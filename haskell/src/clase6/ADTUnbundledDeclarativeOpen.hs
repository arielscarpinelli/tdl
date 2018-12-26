module ADTUnbundledDeclarativeOpen where

stack = []

push stack element = element : stack

pop (head : tail) = (head, tail)

isEmpty [] = True
isEmpty stack = False

main = let s = push (push stack "ariel") "alejandro" in do
    print (isEmpty stack) -- True
    print (isEmpty s) -- False
    print (fst (pop (s))) -- "alejandro"
    print (fst (pop (snd (pop (s))))) -- "ariel"
    print (isEmpty (snd (pop (snd (pop (s)))))) -- True
