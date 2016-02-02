(ns apartial.core)

(defn- _->name [l] (mapv #(if (= % '_) (with-meta (gensym) {:arg-type :formal}) %) l))

(defn- formal? [arg] (= (-> arg meta :arg-type) :formal))

(defmacro apartial [f & args]
  (let [actual (_->name args)
        formal (filter formal? actual)]
    `(fn [~@formal ~'& the-rest#]
         (apply ~f (concat ~actual the-rest#)))))