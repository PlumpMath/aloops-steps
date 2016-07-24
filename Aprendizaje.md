# Aprendizaje

## Relacionado con la modificación de la librería quil.

Para poder usar quil+oscp5 juan ha modificado la librería original de oscp5.

```
(defn -oscEvent
  ([this message]
   (println "osc message received! Message: " message)
   (println "addrpattern:" (.addrPattern message))
   (println "typetag:" (.typetag message))
   (with-applet this
     ((:osc-event (.state this)) message))))
```
He añadido más `(println)` para tener más información sobre lo que se está recibiendo: para saber cuál es el path y el typetag.

```
(gen-interface
 :name quil.OscP5
 :methods [[oscEvent [oscP5.OscMessage] Object]])
```
Docs about gen-interface:
When compiling, **gen-interface** generates compiled bytecode for an interface with the given package-qualified :name, and writes the .class file to the *compile-path* directory. When not compiling, does nothing.
Options should be a set of key/value pairs, all except for :name are
optional:
 **:name a name**  (The package-qualified name of the class to be generated)
 **:extends [interface ...]** (One or more interfaces, which will be extended by this interface.)
 **:methods [ [name [param-types] return-type], ...]**
 (This parameter is used to specify the signatures of the methods of
the generated interface. Do not repeat superinterface signatures
here.)


```
 (gen-class
  :name "quil.Applet"
  :implements [clojure.lang.IMeta quil.OscP5]
  ...)
```
quil.OscP5 added to the list of interfaces, the methods of which will be implemented by the class.

```

(defn applet
   ...
   osc-event-fn      (or (:osc-event options) (fn [message] (println "default osc-event")))
   ...
   applet-state      (merge options
                                 {:state state
                                  :looping? looping?
                                  :on-close on-close-fn
                                  :setup-fn setup-fn
                                  :settings-fn settings-fn
                                  :draw-fn draw-fn
                                  :osc-event osc-event-fn
                                  :renderer renderer
                                  :size size
                                  :display (:display options)
                                  :target-frame-rate (atom 60)
                                  :key-event (atom nil)}
                                 listeners)
 ...)
 ```
Con esta modificación se puede añadir una opción nueva a defsketch o sketch, :osc-event. La función que se le asigne será la que se ejecute cada vez que se reciba un mensaje osc.

Una vez realizadas las modificaciones, para que se pueda usar la librería, es necesario hacer `lein install` en el su directorio. Es recomendable hacer antes `lein clean`.

### Cuestiones que no he averiguado todavía:

- En `project.clj` del proyecto en el que queremos usar la librería, en `:dependencies`, no sé si hay que poner `[quil "2.4.0-osc"]` o `[quil/quil "2.4.0-osc"]`.
Juan lo pone así: `[quil/quil "2.0.1-juan"]`, pero cuando se especifica la librería original, es así: `[quil "2.4.0"]`
He probado a ponerlo de las dos maneras y parece que funciona, aunque no sé si algún error de los que me están dando tiene algo que ver con eso.

- En `project.clj` de la librería en `:resource-paths`, hay que añadir `"lib/oscP5.jar"` Y en la carpeta del proyecto debe haber una carpeta llamada `lib`, en la que se encuentra el archivo `oscP5.jar`

- Otra diferencia es que Juan no especifica `[de.sojamo/oscp5 "0.9.8"]` en el `project.clj` del proyecto en el que queremos usar la librería, en `:dependencies`. Y sin embargo si yo no lo incluyo me da error, me da un `java.lang.ClassNotFoundException oscP5.OscP5.`
Tiene todo esto alguna relación con `:resource-paths`?

- Creo que he descubierto el porqué de los dos anteriores puntos:
"Leiningen 1 expanded glob patterns assigned to :resource-path, which allowed us to add jars to a local directory e.g. lib/jars, set :resource-path to "lib/jars/\*", and lein would add those jars to the classpath.
Leiningen 2 does not support this usage out of the box in order to support the principle that builds should be repeatable (see https://github.com/technomancy/leiningen/wiki/Repeatability)."
Sacado de la página: https://github.com/dchelimsky/lein-expand-resource-paths
Por eso ahora, la única manera de acceder a oscP5 es colocando `[de.sojamo/oscp5 "0.9.8"]` en `:dependencies`

-




## Cómo saber los procesos que la JVM tiene abiertos

Abrir consola en `C:\Program Files\Java\jdk1.7.0_65\bin`
Ejecutar `jps`
Es útil porque a veces cuando nos conectamos a la JVM y cerramos una ventana de quil, no se cierra el proceso (depende de si la opción `:exit-on-close` está activa o no). Con `jps` podemos saber si hay procesos que se han quedado abiertos, sobre todo si estamos ejecutando desde la línea de comandos (desde lighttable es fácil ver si hay conexiones abiertas, ya que se ve en la ventana de connections).




















