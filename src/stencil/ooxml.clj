(ns stencil.ooxml
  "Contains common xml element tags and attributes"
  (:refer-clojure :exclude [val name]))

;; run and properties
(def r :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/r)
(def rPr :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/rPr)

(def t :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/t)

;; paragraph and properties
(def p :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/p)
(def pPr :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/pPr)

(def val :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/val)

(def b :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/b)
(def i :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/i)
(def strike :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/strike)

(def u :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/u)

(def vertAlign :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/vertAlign)

;; Content Break: http://officeopenxml.com/WPtextSpecialContent-break.php
(def br :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/br)

(def w :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/w)

;; XML space attribute (eg.: preserve)
(def space :xmlns.http%3A%2F%2Fwww.w3.org%2FXML%2F1998%2Fnamespace/space)

;; font name
(def name :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/name)


(def style :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/style)
;; id of style definition
(def style-id :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/styleId)

;; relation id used with images
(def r-embed :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2FofficeDocument%2F2006%2Frelationships/embed)
(def r-id :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2FofficeDocument%2F2006%2Frelationships/id)

(def bookmark-start
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/bookmarkStart)


(def fld-char
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/fldChar)

(def tag-instr-text
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/instrText)

(def num-id
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/numId)

(def ilvl
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/ilvl)

(def num-pr
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/numPr)

(def fld-char-type
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/fldCharType)

(def name
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/name)

(def fld-char-type
  :xmlns.http%3A%2F%2Fschemas.openxmlformats.org%2Fwordprocessingml%2F2006%2Fmain/fldCharType)
