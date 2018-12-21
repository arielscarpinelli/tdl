module ConcurrentMapper where

import Control.Concurrent
import Control.Monad

runMapper mvar x mapper = do
  threadDelay 1000000 -- 1 s
  print ("en thread  " ++ (show x))
  putMVar mvar (mapper x)

concurrentMapper x mapper = do
  mvar <- newEmptyMVar
  forkIO (runMapper mvar x mapper)
  print ("cree thread  " ++ (show x))
  return mvar
  

doConcurrentMap (x:xs) mapper = 
  (concurrentMapper x mapper) : (doConcurrentMap xs mapper)
  
doConcurrentMap [] _ = []

doUnfold :: [IO a] -> IO [a] -> IO [a]
doUnfold (x:xs) acc = 
  let xAsList = fmap (\_x -> [_x]) x in
  doUnfold xs (pure (++) <*> acc <*> xAsList)

doUnfold [] acc = acc

unfold x = doUnfold x (pure [])

awaitList :: [MVar a] -> [IO a]
awaitList (x:xs) =
  (takeMVar x) : (awaitList xs)

awaitList [] = []

concurrentMap :: Show a => [a] -> (a -> b) -> IO [b]
concurrentMap list mapper = 
  let 
    awaitableList = unfold (doConcurrentMap list mapper)
    awaitedList = fmap awaitList awaitableList
  in
    join (fmap unfold awaitedList)

main = do
  mapped <- concurrentMap [1, 2, 3, 4, 5] (2*)
  print mapped
