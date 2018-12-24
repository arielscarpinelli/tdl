module ProducerConsumerStream where

import Control.Concurrent
import Control.Concurrent.Chan

doProduce :: Int -> Int -> [Int]
doProduce n max_ =
  if n < max_ then 
    n : (doProduce (n + 1) max_)
  else 
    []

producer max_ = doProduce 0 max_

consumer (x:xs) = do
  if ((x `mod` 2) == 0) then 
    print x
  else
    return ()
  consumer xs  

consumer [] = return ()

main = consumer(producer(50))
