(require '[clojure.test :as t])

(doseq [ns-sym '[talent.methods.test-agent
                  talent.social-test
                  talent.lexicon-contract-test
                  talent.murakumo-test
                  talent.repository-contract-test]]
  (require ns-sym))

(let [result (apply t/run-tests
                    '[talent.methods.test-agent
                      talent.social-test
                      talent.lexicon-contract-test
                      talent.murakumo-test
                      talent.repository-contract-test])]
  (System/exit (if (zero? (+ (:fail result) (:error result))) 0 1)))
