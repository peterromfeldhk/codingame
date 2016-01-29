(ns Player
  (:gen-class))

(defn mountain-count [mountains bool]
  (count (remove bool (map #(zero? (nth % 1)) mountains))))

(def full-round? (atom false))

(defn fire? [x mountains]
  (cond
    (not-any? #(zero? (nth % 1)) mountains) (do (println "FIRE") true)
    (= 1 (mountain-count mountains false?)) (do (println "FIRE") true)
    (and (= 2 (mountain-count mountains false?))
         (= 6 x)) (do (println "FIRE") true)
    (and (= 3 (mountain-count mountains false?))
         (= 1 x)) (do (println "FIRE") true)
    (and (= 4 (mountain-count mountains false?))
         (= 5 x)) (do (println "FIRE") true)
    (and (= 5 (mountain-count mountains false?))
         (= 2 x)) (do (println "FIRE") true)
    (and (= 6 (mountain-count mountains false?))
         (= 4 x)) (do (println "FIRE") true)
    (and (= 7 (mountain-count mountains false?))
         (= 3 x)) (do (println "FIRE") true)
    :else (do (println "HOLD") false)))

(defn shoot-or-hold [{:keys [x]} fired mountains round]
  (let [sorted-mountains (vec (reverse (sort-by #(max (% 1)) mountains)))]
    (cond
      fired (do (println "HOLD") true)
      @full-round? (fire? x mountains)
      (= x (get-in sorted-mountains [0 0])) (do (println "FIRE") true)
      :else (do (println "HOLD") false))))

(defn nothing [v] v)

(defn update-x [space-x inc-dec bool]
  (-> (update-in space-x [:x] inc-dec)
      (assoc :inc bool)))

(defn -main [& args]
  (loop [spaceX {:x 0 :inc true} round 0 fired false]
    (if (= round 19)
      "Crashed"
      (let [_ (read) _ (read)
            mountains (->> (for [_ (range 8)] (read))
                           (map-indexed vector) vec)
            _ (when (and (zero? round)
                         (or (= (count mountains) (mountain-count mountains true?))
                             (= (- (count mountains) 1) (mountain-count mountains true?))))
                (reset! full-round? true))
            fired? (shoot-or-hold spaceX fired mountains round)
            inc? (and (zero? (:x spaceX)) (false? (:inc spaceX)))
            dec? (and (= (:x spaceX) 7) (:inc spaceX))]
        (recur
          (cond
            (and (zero? (:x spaceX)) (zero? round)) (update-x spaceX inc true)
            inc? (update-x spaceX nothing true)
            dec? (update-x spaceX nothing false)
            (:inc spaceX) (update-x spaceX inc true)
            (false? (:inc spaceX)) (update-x spaceX dec false)
            :else (throw "missing cond"))
          (if (or (and (false? (:inc spaceX)) (zero? (:x spaceX)))
                  (= (:x spaceX) 7))
            (inc round)
            round)
          (if (or inc? dec?)
            false
            fired?))))))


#_(binding [*out* *err*]
    (println {:spaceX x
              :fired  fired
              :full? @full-round?})
    (println {:mountains mountains}))

(comment
  "one shot each round"
  "mountains can be on each field"
  "mountains can be on the smae field multiple times"
  "At the start of each game turn, you are given:
  the variable spaceX: the horizontal coordinate of your ship (0 to 7)
  the variable spaceY: the altitude at which your ship is advancing in kilometers (10 to 1)
  the height of each mountain from left to right (8 values mountainH)"
  )