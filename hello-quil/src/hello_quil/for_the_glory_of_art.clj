(ns hello-quil.for-the-glory-of-art
  (:require [quil.core :as q])
  (:gen-class))

(defn setup []
  (q/frame-rate 10)                   ;; Set framerate to 1 FPS
  (q/background 200))                 ;; Set the background colour to
                                      ;; a nice shade of grey.
(defn draw []
  (q/stroke (q/random 255) (q/random 255) (q/random 255))   ;; Set the stroke colour to a random grey
  (q/stroke-weight (q/random 10))                           ;; Set the stroke thickness randomly
  (q/fill (q/random 255) (q/random 255) (q/random 255))     ;; Set the fill colour to a random grey

  (let [diam (q/random 100)             ;; Set the diameter to a value between 0 and 100
        x    (q/random (q/width))       ;; Set the x coord randomly within the sketch
        y    (q/random (q/height))]     ;; Set the y coord randomly within the sketch
    (q/ellipse x y diam diam)))         ;; Draw a circle at x y with the correct diameter

(defn -main [& args]
  (q/sketch                             ;; Define a new sketch (no name because we are using sketch instead of defsketch)
    :title "Oh so many grey circles"    ;; Set the title of the sketch
    :settings #(q/smooth 2)             ;; Turn on anti-aliasing
    :setup setup                        ;; Specify the setup fn
    :draw draw                          ;; Specify the draw fn
    :size [323 200]))

;; Java requires a public main method within a public class to serve as the entry point for the JVM.
;; Using :gen-class allows us to use for-the-glory-of-art/-main as the entry point.
