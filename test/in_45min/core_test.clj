(ns in-45min.core-test
  (:require [in-45min.core :refer :all]
            [expectations :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; 10 second example form http://jayfields.com/expectations/index.html

;; expectations uses the format (expect expected actual) for all tests
;; (equality, expected exception, regex matching, interactions, etc).

;; use expectations to test equality
(expect 1 1)
(expect "foo" "foo")

;; test if the regex is in a string
(expect #"foo" "boofooar")

;; does the form throw an expected exception
(expect ArithmeticException (/ 12 0))

;; expect a value in a collection (k/v for maps)
(expect {:foo 1} (in {:foo 1 :cat 4}))
(expect :foo (in #{:foo :bar}))
(expect :foo (in [:bar :foo]))

;; expect a function to return a truthy value given the actual argument
(expect empty? (list))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(expect
  secret
  (->> secret
       ; decode ;)
       (sort-by first)
       (map second)
       reverse
       (apply str)
       ; recode back to compare
       reverse
       (zipmap (range))))
