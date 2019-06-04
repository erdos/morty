(ns stencil.process
  "These functions are called from Java."
  (:import [java.io File PipedInputStream PipedOutputStream InputStream]
           [java.util.zip ZipEntry ZipOutputStream]
           [io.github.erdos.stencil.impl FileHelper ZipHelper])
  (:require [clojure.java.io :as io]
            [stencil.util :refer [trace]]
            [stencil.model :as model]))

(set! *warn-on-reflection* true)

;; merge a set of fragment names under the :fragments key
(defn- merge-fragment-names [model]
  (assoc model
         :fragments
         (-> #{}
             (into (-> model :main :executable :fragments))
             (into (for [x (:headers+footers (:main model))
                         f (:fragments (:executable x))] f)))))

(defn prepare-template [^InputStream stream]
  (assert (instance? InputStream stream))
  (let [zip-dir   (FileHelper/createNonexistentTempFile "stencil-" ".zip.contents")]
    (with-open [zip-stream stream] ;; FIXME: maybe not deleted immediately
      (ZipHelper/unzipStreamIntoDirectory zip-stream zip-dir))
    (-> zip-dir
        model/load-template-model
        merge-fragment-names)))

(defn prepare-fragment [input]
  (assert (some? input))
  (let [zip-dir (FileHelper/createNonexistentTempFile
                 "stencil-fragment-" ".zip.contents")]
    (with-open [zip-stream (io/input-stream input)]
      (ZipHelper/unzipStreamIntoDirectory zip-stream zip-dir))
    (model/load-fragment-model zip-dir)))

(defn- render-writers-map [writers-map outstream]
  (assert (map? writers-map))
  (with-open [zipstream (new ZipOutputStream outstream)]
    (doseq [[k writer] writers-map
            :let  [rel-path (FileHelper/toUnixSeparatedString (.toPath (io/file k)))
                   ze       (new ZipEntry rel-path)]]
      (assert (not (.contains rel-path "../")))
      (trace "ZIP: writing %s" rel-path)
      (.putNextEntry zipstream ze)
      (writer zipstream)
      (.closeEntry zipstream))))

(defn eval-template [{:keys [template data function fragments] :as args}]
  (assert (:source-folder template))
  (let [data        (into {} data)
        writers-map (model/template-model->writers-map template data function fragments)]
    {:writer (partial render-writers-map writers-map)}))
