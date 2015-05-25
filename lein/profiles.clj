{:user
 {:plugins [[lein-bin "0.3.4"]
            [lein-midje "3.1.3"]
            [cider/cider-nrepl "0.9.0-SNAPSHOT"]    ;;spacemacs cider
            [refactor-nrepl "1.0.5"]]               ;;spacemacs cider
  :dependencies [[alembic "0.3.2"]                  ;;spacemacs cider
                 [org.clojure/tools.nrepl "0.2.7"]  ;;spacemacs cider
                 [im.chit/vinyasa "0.3.4"]
                 [spyscope "0.1.5"]
                 [com.gfredericks/debug-repl "0.0.6"]
                 [debugger "0.1.7"]
                 [difform "1.1.2"]
                 [clj-ns-browser "1.3.1"]
                 ]

  :repl-options {:nrepl-middleware
                 [com.gfredericks.debug-repl/wrap-debug-repl]}
  
  :injections [(require 'spyscope.core)
               (require 'debugger.core)
               ;;Everything next is vinyasa stuff
               (require '[vinyasa.inject :as inject])
               (require 'com.georgejahad.difform)
               (require 'com.gfredericks.debug-repl)
               (require 'clj-ns-browser.sdoc)
               (inject/in 
                          [clojure.repl doc source]
                          [clojure.pprint pprint pp]
                          [vinyasa.inject :refer [inject [in inject-in]]]
                          [vinyasa.pull :all]
                          ;;[vinyasa.reimport reimport reimport-from-file]
                          [com.georgejahad.difform difform]
                          [clj-ns-browser.sdoc sdoc]
                          [cemerick.pomegranate add-classpath get-classpath resources]

                          clojure.core >
                          [clojure.java.shell sh]
                          [com.gfredericks.debug-repl break! unbreak! unbreak!!]
                          [debugger.core break break-catch])
               ;;Personal repl tools :)
               (defn >shell [args] (-> (apply >sh (.split args " ")) :out (.split "\n") ./pprint))]}}

