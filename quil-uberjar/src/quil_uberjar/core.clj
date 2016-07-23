(ns quil-uberjar.core
  (:require [quil.core :as q])
  (:gen-class))

(defn setup []
  (q/smooth)
  (q/frame-rate 1)
  (q/background 200))

(defn draw []
  (q/stroke (q/random 255))
  (q/stroke-weight (q/random 10))
  (q/fill (q/random 255))

  (let [diam (q/random 100)
        x    (q/random (q/width))
        y    (q/random (q/height))]
    (q/ellipse x y diam diam)))

(defn -main [& args]
  (q/sketch
    :title "Oh so many grey circles"
    :setup setup
    :draw draw
    :size [323 200]
    :features [:exit-on-close])) ;; :exit-on-close is not set by default, meaning that when we close the window
                                 ;; the JVM keeps running. That's good for development, because if we close the
                                 ;; JVM we'll need to start a new repl every time we run the sketch.
                                 ;; But before we run "lein uberjar" we should turn it on.

;; La configuración de un archivo para exportarlo a .jar es distinta de la de un archivo
;; en el que estamos trabajando.
;; Mientras estamos desarrollando el proyecto es mejor usar defsketch, que está pensado
;; precisamente para un desarrollo interactivo que permite hacer cambios en el código
;; y verlos en un applet que tengamos abierto sin necesidad de cerrarlo y abrilo de nuevo

;; Nikita lo describe así en la issue #52:
;; defsketch - yes, it is pretty dumb macro that just calls defapplet.
;; But I think it was done it order to call "private" macro defapplet which is defined
;; in another namespace quil.applet which is not exposed in documentation.
;; defapplet - is not useless. It does one pretty important thing:
;; it wraps all functions passed in it to var.
;; It allows to do interactive development which is pretty cool.

;; La clave en todo esto es: ***Wrapping symbols into var allows dynamic replacements.***

;; Sin embargo, para exportar el archivo a .jar es mejor usar sketch. Con defskecth, uberjar no funciona,
;; a no ser que esté dentro de otra función.

;; Otra cuestión es que tenemos que llamar a sketch desde dentro de una función -main. Que es la
;; que lein va a buscar (si lo tenemos configurado en el project.clj) para pasársela a la JVM como
;; entrypoint.






















