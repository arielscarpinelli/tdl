module PatternMatchingAbs where

absInt x = if x < 0 then -x else x

absList (head : tail) = absInt(head) : absList(tail)
absList [] = []

main = do
    print (absList [22, -14, 13, -1, -2, 4])
    print (absList [])