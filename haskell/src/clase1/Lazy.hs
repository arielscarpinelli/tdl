module Lazy where

ints n = n : ints(n + 1)

main = do
    print (head (ints 0))
    print (head (tail (ints 0)))
    print (head (tail (tail (ints 0))))