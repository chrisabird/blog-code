(ns blog-code.finding-similar-colors
  (:import java.lang.Math 
           javax.imageio.ImageIO 
           java.io.File
           java.awt.image.BufferedImage))

;; CIEXYZ to CIELAB

(defn cielab-adjust [component]
  (if (> component 0.008856)
    (Math/pow component (/ 1 3))
    (+ (* 7.787 component) (16 / 116))))

(defn ciexyz-to-cielab [ciexyz]
  (let [x (cielab-adjust (/ (:x ciexyz) 95.047))
        y (cielab-adjust (/ (:y ciexyz) 100.000))
        z (cielab-adjust (/ (:z ciexyz) 108.883))]
    {:l (float (if (> z 0.008856) (- (* 116 y) 16) (* 903.3 y)))
     :a (float (* 500 (- x y)))
     :b (float (* 200 (- y z)))}))

;; RGB to CIEXYZ

(defn ciexyz-adjust [component]
  (* 100
    (let [x (/ component 255)] 
    (if (<= x 0.04045)
      (/ x 12.92)
      (Math/pow (/ (+ x 0.055) 1.055) 2.4)))))

(defn rgb-to-ciexyz [srgb]
  (let [r (ciexyz-adjust (:r srgb))
        g (ciexyz-adjust (:g srgb))
        b (ciexyz-adjust (:b srgb))]
    {:x (float (+ (* 0.4124 r) (* 0.3576 g) (* 0.1805 b)))
     :y (float (+ (* 0.2126 r) (* 0.7152 g) (* 0.0722 b)))
     :z (float (+ (* 0.0193 r) (* 0.1192 g) (* 0.9505 b)))}))


;; RGB from Image

(defn get-rgb-of-xy-in-image [x y img-path]
  (let [img (ImageIO/read (File. img-path))
        rgb (.getRGB img x y)]
    {:r (bit-and (bit-shift-right rgb 16) 255) 
     :g (bit-and (bit-shift-right rgb 8) 255) 
     :b (bit-and rgb 255)}))



;; Euclidian Distance

(defn distance [x y]
  (Math/sqrt
    (+ (Math/pow (- (:l y) (:l x)) 2) 
       (Math/pow (- (:a y) (:a x)) 2) 
       (Math/pow (- (:b y) (:b x)) 2)))) 

