; to make Cursive able to resolve symbols
(require '[boot.core :refer :all])

(set-env!
  :project 'codin-game
  :version "0.4.0"
  :dependencies
  '[[org.clojure/clojure "1.7.0"]

    [camel-snake-kebab "0.3.2"]
    [org.clojure/data.json "0.2.6"]
    [org.clojure/data.csv "0.1.3"]
    [org.clojure/data.xml "0.0.8"]
    [com.rpl/specter "0.9.1"]

    [http-kit "2.1.18"]
    [clj-time "0.11.0"]
    [enlive "1.1.6"]

    [midje "1.8.2"]
    [zilti/boot-midje "0.2.1-SNAPSHOT"]
    [com.taoensso/timbre "4.1.0-alpha2"]
    ]
  :source-paths #{"src" "test"})

;;; Copied from boot-test:
;;; This prevents a name collision WARNING between the test task and
;;; clojure.core/test, a function that nobody really uses or cares
;;; about.
(if ((loaded-libs) 'boot.user)
  (ns-unmap 'boot.user 'test))

(require
  '[zilti.boot-midje :refer [midje] :rename {midje test}])

(def repl-init
  '(require
     '[clojure.inspector :refer :all]
     '[org.httpkit.client :as http]
     '[net.cgrand.enlive-html :as html]
     '[clojure.java.io :as io]
     '[clojure.string :as s]
     '[midje.repl :refer :all]))

(task-options!
  speak {:theme "ordinance"}

  ; lein-generate gets the project name and version from here
  pom {:project (get-env :project)
       :version (get-env :version)}

  ; This option is not used by `boot repl --server`
  ; only by the `boot repl --client`
  repl {:eval repl-init})


