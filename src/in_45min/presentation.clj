(ns in-45min.presentation
  "A couple of helpers to display text on screen."
  (:require [clojure.string :as s]))

; (def ^:dynamic *screen-size*
;   {:width 237
;    :height 50})

(def ^:dynamic *screen-size*
  {:width 88
   :height 20})

(def ^:dynamic *centering*
  {:v-centered true
   :h-centered true})

(def ^:dynamic *slides-count* 0)

(defn check-screen-size
  "A util fn to find out the best width/height values for the current screen."
  ([]
   (check-screen-size
     (:width *screen-size*)
     (:height *screen-size*)))
  ([width height]
   (-> "%sx%s" (format width height) println)
   (dotimes [_ height]
      (dotimes [_ width]
        (print "."))
      (println "|"))))

(defn- line-str
  [s]
  (concat "\n"
          (if (:h-centered *centering*)
            (-> *screen-size* :width (- (count s)) (/ 2) dec (repeat \space))
            (-> *screen-size* :width (/ 25) inc (repeat \space)))
          [s]))

(defn- print-lines
  [lines]
  (let [blank-lines-before  (when (:v-centered *centering*) (-> *screen-size* :height (- (count lines)) (/ 2) (repeat \newline) s/join))
        blank-lines-after   (if (:v-centered *centering*)
                              blank-lines-before
                              (-> *screen-size* :height (- (count lines)) (repeat \newline) s/join))]
    (when blank-lines-before (print blank-lines-before))
    (->> lines
         (mapcat line-str)
         (apply str)
         println)
    (print blank-lines-after)))

(defn- footnote-and-wait-for-key
  [slide-index]
  ; (println "Press enter for next slide...")
  (print (format "%s/%s" slide-index *slides-count*))
  (flush)
  (read-line))

(defmacro with-centering
  [centerings & body]
  `(binding [*centering* {:v-centered (some #{:vertical} ~centerings)
                          :h-centered (some #{:horizontal} ~centerings)}]
     ~@body))

(defmulti ^:private display-slide ffirst)

(defmethod display-slide :default
  [[slide-type lines slide-index]]
  (println
    (format "[NB: Slide type %s doesn't exists. Must be one of: %s. For now, displaying as :new-slide.]"
             slide-type
             (->> @#'display-slide methods keys (remove #{:default}) (s/join ", "))))
  (display-slide [[:new-slide] lines slide-index]))

(defmethod display-slide :first-slide
  [_]
  (with-centering [:vertical :horizontal]
    (print-lines "")))

(defmethod display-slide :new-slide-centered
  [[_ lines slide-index]]
  (with-centering [:vertical :horizontal]
    (print-lines lines))
  (footnote-and-wait-for-key slide-index))

(defmethod display-slide :new-slide
  [[_ lines slide-index]]
  (with-centering [:none]
    (print-lines lines))
  (footnote-and-wait-for-key slide-index))

(defmethod display-slide :new-slide-list
  [[_ lines slide-index]]
  (with-centering [:none]
    (->> lines
         (map #(str " - " %))
         print-lines))
  (footnote-and-wait-for-key slide-index))

(defmethod display-slide :new-slide-numbered-list
  [[_ lines slide-index]]
  (with-centering [:none]
    (->> lines
         (map-indexed #(str (inc %1) ". " %2))
         print-lines))
  (footnote-and-wait-for-key slide-index))

(defn present
  [& slides]
  (binding [*slides-count* (->> slides (filter keyword?) count)]
    (->> slides
         (concat [:first-slide nil])
         (partition-by keyword?)
         (partition 2)
         (map-indexed #(concat %2 [%1]))
         (map display-slide)
         dorun)))

(defn present-with-screen-size
  [[width height] & slides]
  (binding [*screen-size* {:width width, :height height}]
    (apply present slides)))

(defmacro defpresentation
  [& sections]
  `(do
    ~@(for [[section-name slides] (partition 2 sections)
              :let [fn-symbol (->> section-name name (format "%s") symbol)]]
        `(defn ~fn-symbol
           []
           (present ~@slides)))))
