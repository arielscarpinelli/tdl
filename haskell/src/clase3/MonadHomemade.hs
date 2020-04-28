module MonadHomemade where

-- inputs and outputs
type OutsideWorld = ([String], [String])

type MyIO t = OutsideWorld -> (t, OutsideWorld)

myPrint s (inputs, outputs) = ((), (inputs, outputs ++ [s]))
myRead (i:nputs, outputs) = (i, (nputs, outputs))

bind action1 action2 = \outsideworld ->
  let (action1Result, updatedWorld) = action1 outsideworld
  in action2 action1Result updatedWorld

run ioOp = ioOp (["ariel"], [])

main =
  print (run (
    myPrint "Please enter your name" `bind`
    \ignore -> myRead  `bind`
    \name -> myPrint ("Hello " ++ name)))