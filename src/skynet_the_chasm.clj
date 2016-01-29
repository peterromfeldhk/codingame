(ns Player
  (:gen-class))

(defn jump? [{:keys [speed coords]} road gap & _]
  (>= (+ coords speed) (+ road gap)))

(defn slow? [& [{:keys [speed coords]} road gap platform :as args]]
  (if (>= coords (+ road gap))
    true
    (and (not (apply jump? args))
         (> (+ coords speed) road))))

(defn wait? [& [{:keys [speed coords]} road gap platform :as args]]
  (and (not (apply jump? args))
       (> (+ coords speed 1) road)))

(defn move [& [{:keys [speed coords] :as movement} road gap platform :as args]]
  (cond
    (apply slow? args) (do (println "SLOW") {:speed (- speed 1) :coords (+ coords (- speed 1))})
    (apply wait? args) (do (println "WAIT") (update-in movement [:coords] #(+ coords speed)))
    (apply jump? args) (do (println "JUMP") (update-in movement [:coords] #(+ % speed)))
    :else (do (println "SPEED") {:speed (+ speed 1) :coords (+ coords (+ speed 1))})))

(defn -main [& args]
  (let [road (read) gap (read) platform (read) speed (read) coords (read)]
    (loop [movement {:speed speed :coords coords}]
      (if (and (> coords 0) (zero? (:speed movement)))
        "FINISH!"
        (recur
          (move movement road gap platform))))))

#_(binding [*out* *err*]
    (println {:road road :gap gap :platform platform})
    (println {:speed speed :coordX coordX}))

(comment
  " Initialization input
  Line 1: road the length of the road before the gap.
  Line 2: gap the length of the gap.
  Line 3: platform the length of the landing platform.
  Input for one game turn
  Line 1: speed the motorbike's speed.
  Line 2: coordX the position on the road of the motorbike.
  Output for one game turn
  A single line containing one of 4 keywords: SPEED SLOW JUMP WAIT .
  Constraints
  The initial position of the motorbike is always coordX = 0.
  0 ≤ speed < 50
  0 ≤ coordX < 100
  1 ≤ road ≤ 100
  1 ≤ gap ≤ 100
  1 ≤ platform ≤ 100
  Response time for one game turn ≤ 150ms")