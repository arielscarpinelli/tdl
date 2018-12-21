module Concurrencia where

import Control.Concurrent
import Control.Concurrent.Chan

reduceMVars (mvar:s) reducer acc = do
    value <- takeMVar mvar
    reduceMVars s reducer (reducer value acc)

reduceMVars [] reducer acc = return (acc)

firstThread mv = do
    threadDelay 200000 -- 200 ms
    putMVar mv 4
    print "first done"

secondThread mvIn mvOut = do
    readValue <- takeMVar mvIn
    threadDelay 200000 -- 200 ms
    putMVar mvOut (readValue + 1)
    print "second done"

thirdThread mv = do
    threadDelay 100000 -- 100 ms
    putMVar mv 2
    print "third done"

fourthThread mvsIn mvOut = do
    sum <- reduceMVars mvsIn (+) 0
    putMVar mvOut sum
    print "fourth done"

main = do
  mv1 <- newEmptyMVar
  mv2 <- newEmptyMVar
  mv3 <- newEmptyMVar
  mv4 <- newEmptyMVar
  forkIO (firstThread mv1)
  forkIO (secondThread mv1 mv2)
  forkIO (thirdThread mv3)
  forkIO (fourthThread [mv2, mv3] mv4)
  print "im here"
  result <- takeMVar mv4
  print result
