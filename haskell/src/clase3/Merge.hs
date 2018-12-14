module Merge where

merge :: [] Int -> [] Int -> [] Int
merge [] right = right
merge left [] = left
merge (l:eft) (r:ight) =
    if l < r
    then l : (merge eft (r:ight))
    else r : (merge (l:eft) ight)


l1 = [10, 20, 30, 40, 50, 60]
l2 = [2, 4, 5, 7, 13, 14, 22, 45, 60, 100, 150]

main = print (merge l1 l2)