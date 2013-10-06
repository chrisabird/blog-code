(ns blog-code.finding-similar-colors-test
  (:use midje.sweet blog-code.finding-similar-colors))


(fact "can convert CIEXYZ to CIELAB"
  (ciexyz-to-cielab {:x 43.265125 :y 40.449436 :z 40.649162}) => {:l (float 69.788445) 
                                                                  :a (float 14.84633) 
                                                                  :b (float 3.900725)})

(fact "can convert sRGB to cieXyz"
      (rgb-to-ciexyz {:r 200 :g 161 :b 164}) => {:x (float 43.265125) 
                                                 :y (float 40.449436) 
                                                 :z (float 40.649162)})

(fact "can get a rgb color from a xy in an image"
  (get-rgb-of-xy-in-image 0 0 "resources/plastic-bird.jpg") => {:r 200 :g 161 :b 164})

(fact "can calculate euclidian distance"
  (distance 
    {:l 67.467419 :a 29.989378 :b 6.301008} 
    {:l 42.403418 :a 45.709176 :b 10.237176}) => 29.846433854198214)
