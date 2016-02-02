(ns apartial.core)


(defn _->name [l]
  (map (fn [x1 x2] (if (= x1 '_) (with-meta (symbol (str "x" x2)) {:arg-type :formal}) x1)) l (-> l count range)))

(defn formal? [arg]
  (= (-> arg meta :arg-type) :formal))

(defmacro apartial [f & args]
  `(fn [~'& ~'as]
     (println (str "args: " '~args))
     ;(apply ~f (concat ~args ~'as))
     )
  )


(comment 
  (apartial + _ 2 _)
  should be transformed to
  (fn [x0 x1 & args]
    (apply + (concat [x0 2 x1] args))) 
  )