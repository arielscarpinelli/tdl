module PatternMatchingLength where

doLength (head:tail) acc = doLength tail (acc + 1)
doLength [] acc = acc

listLength list = doLength list 0


main = do
    print (listLength [1, 2, 3])
    print (listLength [1])
    print (listLength [])
