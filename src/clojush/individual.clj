(ns clojush.individual)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Individuals are records.
;; Populations are vectors of agents with individuals as their states (along with error and
;; history information).

(defrecord individual [genome program errors total-error normalized-error weighted-error history ancestors parent])

(defn make-individual [& {:keys [genome program errors total-error normalized-error weighted-error history ancestors parent]
                          :or {genome nil
                               program nil
                               errors nil
                               total-error nil ;; a non-number is used to indicate no value
                               normalized-error nil
                               weighted-error nil
                               history nil
                               ancestors nil
                               parent nil}}]
  (individual. genome program errors total-error normalized-error weighted-error history ancestors parent))

(defn printable [thing]
  (letfn [(unlazy [[head & tail]]
                  (cons (if (seq? head) (unlazy head) head)
                        (if (nil? tail) tail (unlazy tail))))]
    (cond (seq? thing) (unlazy thing)
          (nil? thing) 'nil
          :else thing)))

(defn individual-string [i]
  (cons 'individual.
        (let [k '(:genome :program :errors :total-error :normalized-error :weighted-error :history :ancestors :parent)]
          (interleave k  (map #(printable (get i %)) k)))))

