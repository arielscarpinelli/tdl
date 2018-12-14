module Split where

doSplit :: [] Int -> [] Int -> [] Int -> ([] Int, [] Int)

doSplit (x:y:rest) accL accR = doSplit rest (accL ++ [x]) (accR ++ [y])
doSplit (x:[]) accL accR = (accL ++ [x], accR)
doSplit _ accL accR = (accL, accR)


split list = doSplit list [] []

l1 = [10, 20, 30, 40, 50, 60, 70, 80, 90]
(l2, l3) = split(l1)

main = do
    print l2
    print l3