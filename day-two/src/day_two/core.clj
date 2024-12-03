(ns day-two.core
  (:require [clojure.string :as str]))

(defn is-safe? [report]
  (if
    (and
      (or
        (apply < report)
        (apply > report))
      (loop [nums report
             status true]
        (let [nums-to-check (take 2 nums)]
          (if (and (< 1 (count nums-to-check)) status)
            (let [diff (abs (- (first nums-to-check) (second nums-to-check)))]
             (recur (rest nums) (if (and (<= 1 diff) (<= diff 3)) true false))) 
            status))))
    true
    false))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [test-mode false] 
   (with-open [rdr (clojure.java.io/reader (if test-mode "test-input.txt" "input.txt"))]
      (->> (line-seq rdr) 
           (into [])
           (map #(str/split % #" "))
           (map #(map parse-long %))
           (map is-safe?)
           (filter true?)
           (count)
           ))))
