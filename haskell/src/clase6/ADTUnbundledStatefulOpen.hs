module ADTUnbundledStatefulOpen where

import Debug.Trace
import Data.IORef

stack = newIORef []

push stack element = modifyIORef stack (element : )

pop stack = do
  current <- readIORef stack
  case current of
    (head : tail) -> do
      writeIORef stack tail
      return head

isEmpty stack = do
  current <- readIORef stack
  return (case current of
    [] -> True
    _ -> False)

main = do
  s <- stack
  isEmpty s >>= traceShowM -- True
  push s "ariel"
  push s "alejandro"
  isEmpty s >>= traceShowM -- False
  pop s >>= traceShowM -- "alejandro"
  pop s >>= traceShowM -- "ariel"
  isEmpty s >>= traceShowM -- True
