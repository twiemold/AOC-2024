(ns day-one.core
  (:require [ clojure.string :as str ]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [test-mode false
        data (slurp (if test-mode "test-input.txt" "input.txt"))
        ids (map parse-long (str/split data #"\s\s\s|\n"))
        l-ids (sort (take-nth 2 ids) )
        r-ids (sort (take-nth 2 (drop 1 ids)))
        freq-r-ids (frequencies r-ids)]
    ;; pt. one
    ;;(reduce + (map abs (map #(reduce - %) (partition 2 (interleave r-ids l-ids)))))
    (->> l-ids
         (map #(get freq-r-ids % 0))
         (interleave l-ids)
         (partition 2)
         (map #(reduce * %))
         (reduce +))))
