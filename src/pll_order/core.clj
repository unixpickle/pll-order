(ns pll-order.core)
(load "core_perms")
(load "core_moves")
(load "core_plls")

(defn find-identity-perm
  []
  (loop []
    (let [s (shuffle pll-keywords)
          perms (map plls s)
          cube (apply comp perms)]
      (if (cubes-equal identity-perm cube) s (recur)))))

(defn compute-percent-solved
  []
  (loop [i 1000000 c 0]
    (if (zero? i)
        (float (/ c 10000))
        (let [s (shuffle pll-keywords)
              perms (map plls s)
              cube (apply comp perms)]
          (if (cubes-equal identity-perm cube)
              (recur (dec i) (inc c))
              (recur (dec i) c))))))

(defn -main
  "Find an order of PLLs to arrive at the identity."
  []
  (println "Finding an identity order...")
  (println (map #(subs (str %) 1) (find-identity-perm)))
  (println "Computing statistics...")
  (println "Roughly" (compute-percent-solved) "% are solved."))
