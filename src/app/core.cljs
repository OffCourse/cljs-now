(ns app.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defonce express (nodejs/require "express"))
(defonce serve-static (nodejs/require "serve-static"))
(defonce http (nodejs/require "http"))

(def app (express))

(. app (get "/" (fn [req res] (. res (send "HELLO WORLD")))))

(. app (use (serve-static "resources/public" #js {:index "index.html"})))

(def -main
  (fn []
    (doto (.createServer http #(app %1 %2))
      (.listen 3000))))

(set! *main-cli-fn* -main)
