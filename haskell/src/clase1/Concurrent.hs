module Concurrent where

import Control.Concurrent
import Control.Concurrent.Chan

firstThread ch = do
    threadDelay 2000000 -- 2 seconds
    writeChan ch "firstThread says hello"

secondThread ch = do
    threadDelay 3000000
    writeChan ch "secondThread says hola"


main = do
  ch <- newChan
  forkIO (firstThread ch)
  forkIO (secondThread ch)
  print "im here"
  readChan ch >>= print
  readChan ch >>= print
