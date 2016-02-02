(ns apartial.core-test
  (:require [clojure.test :refer :all]
            [apartial.core :refer :all]))

(defn zero-arity [] :zero)

(defn one-arity [a] a)

(defn two-arity [a1 a2] (+ a1 a2))

(defn three-arity [a1 a2 a3] (+ a1 a2 a3))

(defn four-arity [a1 a2 a3 a4] (+ a1 a2 a3 a4))

(defn var-arity [& args] (apply + args))

(deftest norman-case
  (is (= :zero ((apartial zero-arity))))
  (is (= 1 ((apartial one-arity) 1)))
  (is (= 3 ((apartial two-arity) 1 2)))
  (is (= 6 ((apartial three-arity) 1 2 3)))
  (is (= 10 ((apartial four-arity) 1 2 3 4)))
  (is (= 15 ((apartial var-arity) 1 2 3 4 5)))
  )

(deftest with_underscore
  (is (= 1 ((apartial one-arity _) 1)))
  (is (= 3 ((apartial two-arity _ 2) 1)))
  (is (= 6 ((apartial three-arity _ _ 3) 1 2)))
  (is (= 6 ((apartial three-arity 1 _ 3) 2)))
  (is (= 6 ((apartial three-arity _ 2 _) 1 3)))  
  (is (= 15 ((apartial var-arity _ 2 _ 4 _) 1 3 5)))
  (is (= 15 ((apartial var-arity _ _ _ _ _) 1 2 3 4 5)))
  )

