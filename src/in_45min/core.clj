(ns in-45min.core
  "Prepared forms to use during the presentation."
  (:require [clojure.string :as s]
            [in-45min.presentation :refer [defpresentation] :as presentation]))

(def mail "some@mail.com")

(def secret
  {0 \!
   7 \n
   1 \s
   4 \e
   15 \l
   13 \v
   6 \t
   3 \s
   12 \e
   2 \i
   11 \p
   9 \r
   5 \h
   14 \o
   16 \i
   10 \a
   8 \e})

(defpresentation
  :s1-intro     [
                 :new-slide-centered
                  ""
                  ""
                  "(into clojure)"
                  ""
                  ""
                  ""
                 :new-slide-centered
                  "Welcome"
                  ""
                  "(into clojure)"
                  ""
                  "Name"
                  mail
                 :new-slide-centered
                  "GOALS"
                  "====="
                  ""
                  ""
                  "1. Get into clojure as much as we can in 45min."
                  "2. Give you some useful pointers to continue on your own."
                  ]
  :s2-special-forms [
                :new-slide-centered
                  "Special Forms"
                  "============="
                  ""
                  "Only 11 special forms."
                  "Everything else is derived from those."
                  ""
                  "http://clojure.org/special_forms"
                :new-slide-numbered-list
                  "def"
                  "if"
                  "do"
                  "let"
                  "quote"
                  "var"
                  "fn"
                  "loop"
                  "recur"
                  "throw"
                  "try"
                :new-slide-numbered-list
                  "def       => (def symbol init?)"
                  "if        => (if test then else?)"
                  "do        => (do exprs*)"
                  "let       => (let [bindings* ] exprs*)"
                  "quote     => (quote form)"
                  "var       => (var symbol)"
                  "fn        => (fn name? [params* ] exprs*)..."
                  "loop      => (loop [bindings* ] exprs*)"
                  "recur     => (recur exprs*)"
                  "throw    => (throw expr)"
                  "try      => (try expr* catch-clause* finally-clause?)"
                ]
  :s3-end      [
                :new-slide-centered
                  "And from here...?"
                :new-slide
                  ""
                  "Online documentation"
                  "--------------------"
                  ""
                  "- Official Clojure page:           http://clojure.org"
                  "- Clojure cheatsheet:              http://jafingerhut.github.io"
                  "- Clojure Style Guide:             http://github.com/bbatsov/clojure-style-guide"
                  "- Clojure for the Brave and True:  http://braveclojure.com" ; "an Online Book for Beginners"
                  ""
                  ""
                  "Online learning resources"
                  "-------------------------"
                  ""
                  "- Learn Clojure:                   http://learn-clojure.com"
                  "- 4clojure:                        http://4clojure.com"
                  "- Try Clojure Online:              http://tryclj.com"
                  "- Clojure Koans:                   http://clojurekoans.com"
                  ""
                :new-slide
                  ""
                  "Books"
                  "-----"
                  ""
                  "- Clojure Programming"
                  "  O'Reilly"
                  "  By Chas Emerick, Brian Carper and Christophe Grand"
                  "  http://www.clojurebook.com"
                  ""
                  "- Programming Clojure (2nd edition)"
                  "  The Pragmatic Bookshelf"
                  "  By Stuart Halloway and Aaron Bedra"
                  "  https://pragprog.com/book/shcloj2/programming-clojure"
                  ""
                  "- The Joy of Clojure, Second Edition"
                  "  Manning"
                  "  By Michael Fogus and Chris Houser"
                  "  http://www.manning.com/fogus2/"
                :new-slide-centered
                  "(the end)"
                  ""
                  ""
                  ""
                  ""
                  ""
                  ""
                  ""
                  ""
                :new-slide-centered
                  "(the end)"
                  ""
                  ""
                  "Thanks!"
                  ""
                  "https://github.com/into-clojure/in-45min"
                  ""
                  mail
                  ""
                  ])
