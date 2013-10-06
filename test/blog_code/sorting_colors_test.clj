(ns blog-code.sorting-colors-test
  (:use midje.sweet blog-code.sorting-colors))

(def red-colors [{:rgbR 170 :rgbG 63  :rgbB 85  :hex "AA3F55" :cieB 10.237176  :cieA 45.709176  :cieL 42.403418}
                 {:rgbR 219 :rgbG 144 :rgbB 153 :hex "DB9099" :cieB 6.301008  :cieA 29.989378  :cieL 67.467419} 
                 {:rgbR 241 :rgbG 167 :rgbB 162 :hex "F1A7A2" :cieB 12.878734  :cieA 27.208515  :cieL 75.409492} 
                 {:rgbR 243 :rgbG 209 :rgbB 201 :hex "F3D1C9" :cieB 7.402528  :cieA 11.011979  :cieL 86.365358} 
                 {:rgbR 239 :rgbG 217 :rgbB 208 :hex "EFD9D0" :cieB 6.14829  :cieA 6.803851  :cieL 88.235375}
                 {:rgbR 241 :rgbG 229 :rgbB 220 :hex "F1E5DC" :cieB 4.952257  :cieA 2.911865  :cieL 91.759237} 
                 {:rgbR 132 :rgbG 35  :rgbB 40  :hex "842328" :cieB 21.147956  :cieA 41.619145  :cieL 30.120421} 
                 {:rgbR 183 :rgbG 119 :rgbB 116 :hex "B77774" :cieB 11.574657  :cieA 25.096364  :cieL 56.60262} 
                 {:rgbR 210 :rgbG 154 :rgbB 152 :hex "D29A98" :cieB 8.332442  :cieA 21.283814  :cieL 68.747142} 
                 {:rgbR 226 :rgbG 195 :rgbB 191 :hex "E2C3BF" :cieB 5.42103  :cieA 10.76443  :cieL 81.275439} 
                 {:rgbR 238 :rgbG 222 :rgbB 215 :hex "EEDED7" :cieB 4.358549  :cieA 4.837221  :cieL 89.610957} 
                 {:rgbR 113 :rgbG 42  :rgbB 41  :hex "712A29" :cieB 16.798323  :cieA 31.312469  :cieL 27.612414} 
                 {:rgbR 175 :rgbG 81  :rgbB 66  :hex "AF5142" :cieB 26.435536  :cieA 37.486212  :cieL 46.139487} 
                 {:rgbR 215 :rgbG 147 :rgbB 128 :hex "D79380" :cieB 19.57072  :cieA 23.700833  :cieL 67.122464} 
                 {:rgbR 172 :rgbG 86  :rgbB 142 :hex "AC568E" :cieB -15.378832  :cieA 42.738613  :cieL 48.722962} 
                 {:rgbR 231 :rgbG 217 :rgbB 227 :hex "E7D9E3" :cieB -4.214256  :cieA 7.005148  :cieL 87.975361}])

(fact "can sort colors by rgb"
  (first (sort-by-rgb red-colors)) 
      => (contains {:rgbR 113 :rgbG 42 :rgbB 41})
  (last (sort-by-rgb red-colors)) 
      => (contains {:rgbR 243 :rgbG 209 :rgbB 201})) 

(fact "can convert from cielab to cielch"
  (cielab-to-cielch {:cieL 88.0459 :cieA 6.5440 :cieB -3.1817}) 
      => (contains {:cieC 7.2764792922126835 :cieH 115.92907185324125})) 

(fact "can sort colors by cielch"
  (first (sort-by-cielch red-colors)) 
      => (contains {:cieC 5.744894008793722 :cieH 30.45499414831985})
  (last (sort-by-cielch red-colors)) 
      => (contains {:cieC 8.175087286105267 :cieH 121.03086930350263})) 
