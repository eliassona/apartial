(ns apartial.core)

(defn _->name [l] (mapv (fn [x1 x2] (if (= x1 '_) (with-meta (symbol (str "x" x2)) {:arg-type :formal}) x1)) l (-> l count range)))

(defn formal? [arg] (= (-> arg meta :arg-type) :formal))

(defmacro apartial [f & args]
  (let [actual (_->name args)
        formal (filter formal? actual)]
    `(fn [~@formal ~'& the-rest#]
         (apply ~f (concat ~actual the-rest#)))))