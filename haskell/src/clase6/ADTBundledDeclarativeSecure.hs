module ADTBundledDeclarativeSecure where

data Stack a = Stack {
  push :: a -> Stack a,
  pop :: (a, Stack a),
  isEmpty :: Bool
}

push_ content element = stackOps (element : content)
pop_ (head : tail) = (head, stackOps tail)

isEmpty_ [] = True
isEmpty_ content = False

stackOps content =
  Stack
    (push_ content)
    (pop_ content)
    (isEmpty_ content)

stack = stackOps []

main = let s = push (push stack "ariel") "alejandro" in do
    print (isEmpty stack) -- True
    print (isEmpty s) -- False
    print (fst (pop (s))) -- "alejandro"
    print (fst (pop (snd (pop (s))))) -- "ariel"
    print (isEmpty (snd (pop (snd (pop (s)))))) -- True
