module LazyGenerate where

generate n = n : generate(n + 1)

main = do
    print (head (generate 0))
    print (head (tail (generate 0)))
    print (head (tail (tail (generate 0))))