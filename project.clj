(defproject
  codin-game
  "0.4.0"
  :dependencies
  [[org.clojure/clojure "1.7.0"]
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
   [com.taoensso/timbre "4.1.0-alpha2"]]
  :source-paths
  ["src" "test"])