module Lists where

l1 = [1, 2, 3]
l2 = 1 : 2 : 3 : []

main = do
    print l1
    print (head l1)
    print (tail l1)

    print (head (tail l1))
    print (tail (tail (tail l1)))

    print l2
    print (l1 == l2)