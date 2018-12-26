module ADTUnbundledStateMonadOpen where

import Debug.Trace
import Control.Monad.Trans.State

type Stack a = State [a]

stack = []

push element = state (doPush element)
doPush element stack = ((), element : stack)

pop :: Stack a a
pop = state doPop
doPop (head : tail) = (head, tail)

isEmpty :: Stack a Bool
isEmpty = state checkEmpty
checkEmpty [] = (True, [])
checkEmpty stack = (False, stack)


test = do
  isEmpty >>= traceShowM -- True
  push "ariel"
  push "alejandro"
  isEmpty >>= traceShowM -- False
  pop >>= traceShowM -- "alejandro"
  pop >>= traceShowM -- "ariel"
  isEmpty >>= traceShowM -- True

main = runState test stack
