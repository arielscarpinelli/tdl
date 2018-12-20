module Concurrencia where

import Control.Concurrent
import Control.Concurrent.Chan

reduceChannel (ch:s) reducer acc = do
    value <- readChan ch
    reduceChannel s reducer (reducer value acc)

reduceChannel [] reducer acc = return (acc)

firstThread ch = do
    threadDelay 200000 -- 200 ms
    writeChan ch 4
    print "first done"

secondThread chIn chOut = do
    readValue <- readChan chIn
    writeChan chOut (readValue + 1)
    print "second done"

thirdThread ch = do
    threadDelay 100000 -- 100 ms
    writeChan ch 2
    print "third done"

fourthThread chIns chOut = do
    sum <- reduceChannel chIns (+) 0
    writeChan chOut sum
    print "fourth done"

main = do
  ch1 <- newChan
  ch2 <- newChan
  ch3 <- newChan
  ch4 <- newChan
  forkIO (firstThread ch1)
  forkIO (secondThread ch1 ch2)
  forkIO (thirdThread ch3)
  forkIO (fourthThread [ch2, ch3] ch4)
  print "im here"
  readChan ch4 >>= print