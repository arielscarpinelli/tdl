module ADTBundledStatefulSecure where

import Data.IORef

data Stack a = Stack {
  push :: a -> IO (),
  pop :: IO a,
  isEmpty :: IO Bool
}

push_ stack element = modifyIORef stack (element : )

pop_ stack = do
  current <- readIORef stack
  case current of
    (head : tail) -> do
      writeIORef stack tail
      return head

isEmpty_ stack = do
  current <- readIORef stack
  return (case current of
    [] -> True
    _ -> False)


stack = do
  content <- newIORef []
  return (Stack
    (push_ content)
    (pop_ content)
    (isEmpty_ content))

main = do
  s <- stack
  isEmpty s >>= print -- True
  push s "ariel"
  push s "alejandro"
  isEmpty s >>= print -- False
  pop s >>= print -- "alejandro"
  pop s >>= print -- "ariel"
  isEmpty s >>= print -- True


