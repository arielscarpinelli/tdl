module ADTUnbundledStatefulSecure where

import Debug.Trace
import Data.IORef

-- private type
data Key = Key

data Wrapper t = Wrapper {
  -- data is actually stored in the closure's environment
  content :: Key -> [t]
}

wrap value = Wrapper (\k -> value)

unwrap wrapper key = ((content wrapper) key)

stack = newIORef (wrap [])

push stack element = modifyIORef stack (\s -> wrap (element : (unwrap s Key)))

pop stack = do
  current <- readIORef stack
  case (unwrap current Key) of
    (head : tail) -> do
      writeIORef stack (wrap tail)
      return head

isEmpty stack = do
  current <- readIORef stack
  return (case (unwrap current Key) of
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
g