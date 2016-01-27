(ns Player
  (:gen-class))

(defn move [{:keys [x y] :as cords} goal]
  (cond
    (and (> (get goal :x) x) (> (get goal :y) y))
    (do (println "SE") (-> (update-in cords [:x] dec)
                           (update-in [:y] inc)))
    (and (> (get goal :x) x) (< (get goal :y) y))
    (do (println "NE") (-> (update-in cords [:x] dec)
                           (update-in [:y] dec)))
    (and (< (get goal :x) x) (> (get goal :y) y))
    (do (println "SW") (-> (update-in cords [:x] inc)
                           (update-in [:y] inc)))
    (and (< (get goal :x) x) (< (get goal :y) y))
    (do (println "NW") (-> (update-in cords [:x] inc)
                           (update-in [:y] dec)))
    (> (get goal :y) y) (do (println "S") (update-in cords [:y] inc))
    (< (get goal :y) y) (do (println "N") (update-in cords [:y] dec))
    (> (get goal :x) x) (do (println "E") (update-in cords [:x] dec))
    (< (get goal :x) x) (do (println "W") (update-in cords [:x] inc))))

(defn move-to-light [init-cords goal]
  (loop [cords init-cords]
    (if (= cords goal)
      "Thunder"
      (recur (move cords goal)))))

(defn -main [& args]
  (let [lightX (read) lightY (read) initialTX (read) initialTY (read)]
    (move-to-light {:x initialTX :y initialTY} {:x lightX :y lightY})))