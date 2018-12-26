module ADTUnbundledDeclarativeSecure(Wrapper, stack, push, pop, isEmpty) where

-- private type
data Key = Key

data Wrapper t = Wrapper {
  -- data is actually stored in the closure's environment
  content :: Key -> [t]
}

wrap value = Wrapper (\k -> value)

unwrap wrapper key = ((content wrapper) key)

stack = wrap []

push stack element = wrap (element : (unwrap stack Key))

doPop (head : tail) = (head, wrap tail)
pop stack = doPop (unwrap stack Key)

isEmpty stack = checkEmpty (unwrap stack Key)
checkEmpty [] = True
checkEmpty stack = False

main = let s = push (push stack "ariel") "alejandro" in do
    print (isEmpty stack) -- True
    print (isEmpty s) -- False
    print (fst (pop (s))) -- "alejandro"
    print (fst (pop (snd (pop (s))))) -- "ariel"
    print (isEmpty (snd (pop (snd (pop (s)))))) -- True
