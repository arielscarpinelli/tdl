module PatternMatchingMax where

doMax (head:tail) acc = doMax tail (if head > acc then head else acc)
doMax [] acc = acc

maxList list = doMax list minBound :: Int

main = do
    print (maxList [12, 3, 45, 22, 115, 4])
    print (maxList [])