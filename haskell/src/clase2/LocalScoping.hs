module LocalScoping where

x = 1

someProc = do
    let x = 2
    print x


main = do
    someProc
    print x


