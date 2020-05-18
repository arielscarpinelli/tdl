module CalcServerAsync where


import Control.Concurrent
import Control.Concurrent.Chan

data ServerMessage = 
  Calc {
    x :: Int,
    sender :: Chan ClientMessage
  }

data ClientMessage = 
  Work {
    serverCh :: Chan ServerMessage
  }  | Result {
    result :: Int
  }


consumer f ch state = do
  v <- readChan ch
  newState <- (f ch v state)
  consumer f ch newState

statefulConsumer f initialState = do
  ch <- newChan
  
  forkIO (consumer f ch initialState)
  
  return ch

server ch (Calc x sender) state = do
  print ("server received " ++ (show x))
  threadDelay 1000000 -- 1s
  writeChan sender (Result (x * x + 1))
  return state

client ch (Work serverCh) state = do
  print "client will send 4"
  writeChan serverCh (Calc 4 ch)
  
  print "client will send 5"
  writeChan serverCh (Calc 5 ch)
  
  print "I can calculate a big factorial while waiting"
  threadDelay 3000000 -- 3s
  print "Now Im done with it"

  return state


client ch (Result r) state =
  let newState = (r : state) in do
    print ("client received result: " ++ (show r))

    if (length newState) == 2 then do
      print ("final result: " ++ (show (foldl (+) 0 newState)))
    else
      return ()

    return newState

main = do
  serverCh <- statefulConsumer server ()
  clientCh <- statefulConsumer client []
  writeChan clientCh (Work serverCh)
  threadDelay 10000000 -- 10s
