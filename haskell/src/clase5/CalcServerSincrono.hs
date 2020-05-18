module CalcServerSincrono where


import Control.Concurrent
import Control.Concurrent.Chan

data Message = 
  Calc {
    x :: Int,
    sender :: Chan Message
  } | Result {
    result :: Int
  }

consumer f ch = do
  v <- readChan ch
  f v
  consumer f ch

consumer f = do
  ch <- newChan
  
  forkIO (consumer f ch)
  
  return ch

server (Calc x sender) = do
  print ("server received " ++ (show x))
  threadDelay 1000000 -- 1s
  writeChan sender (Result (x * x + 1))
  

main = do
  ch <- consumer server
  client <- newChan
  
  print "client will send 4"
  writeChan ch (Calc 4 client)
  firstResult <- readChan client
  
  print "client will send 5"
  
  writeChan ch (Calc 5 client)
  secondResult <- readChan client
  
  print ("final result: " ++ (show ((result firstResult) + (result secondResult))))
  
