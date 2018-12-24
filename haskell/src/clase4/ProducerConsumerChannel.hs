module ProducerConsumerChannel where

import Control.Concurrent
import Control.Concurrent.Chan

doProduce :: Chan Int -> Int -> Int -> IO ()
doProduce ch n max_ =
  if n < max_ then 
    do
      writeChan ch n
      doProduce ch (n+1) max_
  else 
    return ()

producer ch max_ = doProduce ch 0 max_

consumer ch = do
  n <- readChan ch
  if ((n `mod` 2) == 0) then 
    print n
  else
    return ()
  consumer ch


main = do
  ch <- newChan
  forkIO (producer ch 50)
  forkIO (consumer ch)
  -- this thread won't wait until the others are done, so to make it simpler, just sleep
  threadDelay 10000000 -- 10s
