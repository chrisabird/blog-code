(defproject blog-code "0.1.0-SNAPSHOT"
  :description "Code and tests for blog posts"
  :url "http://www.github.com/chrisabird/blog-code"
  :license {:name "MIT"
            :url "http://mit-license.org/"}
  :profiles {:dev {:plugins [[lein-midje "3.1.2"]]}}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.2.0"]
                 [midje "1.5.1"]])
