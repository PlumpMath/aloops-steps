(ns hello-quil.mouse01
  (:require [quil.core :refer :all]))

(defn setup []
  (smooth)
  (no-stroke)
  (set-state! :mouse-position (atom [0 0])))

(defn draw
  []
  (background-float 125)
  (stroke-weight 20)
  (stroke-float 10)
  (let [[x y] @(state :mouse-position)]
    (point x y)))

(defn mouse-moved []
  (let [x (mouse-x)  y (mouse-y)]
    (reset! (state :mouse-position) [x y])))

(defn mouse-clicked []
  (println "mouse-clicked"))

(defsketch mouse-example
  :title "Mouse example."
  :size [200 200]
  :setup setup
  :draw draw
  :mouse-moved mouse-moved
  :mouse-clicked mouse-clicked)

(defn -main [& args])

(def bg [25 25 25])

(defn alter-bg-color []
  (map #(* % (rand-int 10)) bg))

(alter-bg-color)
