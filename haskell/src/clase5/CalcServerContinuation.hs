module CalcServerContinuation where


import Control.Concurrent
import Control.Concurrent.Chan

data ServerMessage = 
  Calc {
    x :: Int,
    sender :: Chan ClientMessage
  } | ContResponse {
    x :: Int,
    y :: Int,
    sender :: Chan ClientMessage
  }

data ClientMessage = 
  Work {
    serverCh :: Chan ServerMessage
  } | Cont  {
    c :: Int,
    contCh :: Chan ServerMessage
  } | Result {
    result :: Int
  }


reader f ch state = do
  v <- readChan ch
  newState <- (f ch v state)
  reader f ch newState

channelWithStatefulReader f initialState = do
  ch <- newChan
  
  forkIO (reader f ch initialState)
  
  return ch


doWork ch (Calc x sender) = do
  threadDelay 1000000 -- 1s
  writeChan sender (Cont (x * x) ch)
  return True

doWork ch (ContResponse x y sender) = do
  threadDelay 1000000 -- 1s
  writeChan sender (Result (x + y))
  return False

worker ch = do
  v <- readChan ch
  keepAlive <- doWork ch v
  if keepAlive then
    worker ch
  else
    return ()

server ch (Calc x sender) state = do
  print ("server received " ++ (show x))
  workerCh <- newChan
  forkIO (worker workerCh)
  writeChan workerCh (Calc x sender)
  return state

client ch (Work serverCh) state = do
  print "client will send 4"
  writeChan serverCh (Calc 4 ch)

  print "client will send 5"
  writeChan serverCh (Calc 5 ch)

  return state

client ch (Cont cont contCh) state = do
  print ("progress " ++ (show cont))
  writeChan contCh (ContResponse cont 1 ch)

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
  serverCh <- channelWithStatefulReader server ()
  clientCh <- channelWithStatefulReader client []
  writeChan clientCh (Work serverCh)
  threadDelay 10000000 -- 10s
