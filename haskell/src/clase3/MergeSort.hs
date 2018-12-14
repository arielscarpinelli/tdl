module MergeSort where


doSplit :: [] Int -> [] Int -> [] Int -> ([] Int, [] Int)

doSplit (x:y:rest) accL accR = doSplit rest (accL ++ [x]) (accR ++ [y])
doSplit (x:[]) accL accR = (accL ++ [x], accR)
doSplit _ accL accR = (accL, accR)


split list = doSplit list [] []

merge :: [] Int -> [] Int -> [] Int
merge [] right = right
merge left [] = left
merge (l:eft) (r:ight) =
    if l < r
    then l : (merge eft (r:ight))
    else r : (merge (l:eft) ight)


mergeSort :: [] Int -> [] Int
mergeSort [] = []
mergeSort (x:[]) = [x]
mergeSort list =
  let (xs, ys) = split(list)
  in
    merge (mergeSort xs) (mergeSort ys)


list = [10, 5, 30, 33, 3, 54, 40, 22, 5054, 200, 1000, 51]

main = print (mergeSort list)


