(ns Player
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn -main [& args]
  (while true
    (let [spaceX (read) spaceY (read)]
      (loop [i 8]
        (when (> i 0)
          (let [mountainH (read)]
            ; mountainH: represents the height of one mountain, from 9 to 0. Mountain heights are provided from left to right.
            (recur (dec i)))))

      ; (binding [*out* *err*]
      ;   (println "Debug messages..."))

      ; either:  FIRE (ship is firing its phase cannons) or HOLD (ship is not firing).
      ; (println "HOLD")
      )))