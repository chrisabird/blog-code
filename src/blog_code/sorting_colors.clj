(ns blog-code.sorting-colors
  (:import java.lang.Math))

;; Sorting by sRGB
(defn sort-by-rgb [colors]
  (sort-by (juxt :rgbR :rgbG :rgbB) colors))


;; CIELAB to CIELCH

(defn cielab-to-cielch [color] 
  (let [l (:cieL color)
        a (:cieA color)
        b (:cieB color)
        h (Math/atan2 a b)
        c (Math/sqrt (+ (Math/pow a 2) (Math/pow b 2)))]
    (if (> h 0) 
      (merge color {:cieC c :cieH (* (/ h (Math/PI)) 180)}) 
      (merge color {:cieC c :cieH (- 360 (* (/ (Math/abs h) (Math/PI)) 180))}))))

;; Sorting by CIELch
(defn sort-by-cielch [colors]
  (sort-by (juxt :cieH :cieL :cieC) (map #(cielab-to-cielch %) colors)))
