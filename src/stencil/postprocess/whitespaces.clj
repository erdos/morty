(ns stencil.postprocess.whitespaces
  (:require [clojure.zip :as zip]
            [stencil.types :refer :all]
            [stencil.util :refer :all]))

;;
;;
;;
;; http://officeopenxml.com/WPtext.php

;; like clojure.walk/postwalk but keeps metadata and calls fn only on nodes
(defn- postwalk-xml [f xml-tree]
  (if (map? xml-tree)
    (f (update xml-tree :content (partial mapv (partial postwalk-xml f))))
    xml-tree))


(def ooxml-t :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/t)

(def space-tag :xml/space)

(def space-entry [:xml/space "preserve"])

(defn- should-fix?
  "We only fix <t> tags where the enclosed string starts or ends with a whitespace."
  [element]
  (boolean
   (when (map? element)
     (when (= ooxml-t (:tag element))
       (when (seq (:content element))
         (or (.startsWith (str (first (:content element))) " ")
            (.startsWith (str (last (:content element))) " ")))))))

(defn- fix-elem [element]
  (assoc-in element [:attrs :xml/space] "preserve"))

(defn fix-whitespaces [xml-tree]
  (postwalk-xml #(if (should-fix? %) (fix-elem %) %) xml-tree))
