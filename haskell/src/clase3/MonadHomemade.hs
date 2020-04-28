module MonadHomemade where

-- inputs and outputs
type OutsideWorld = ([String], [String])

type MyIO t = OutsideWorld -> (t, OutsideWorld)

myPrint s (inputs, outputs) = ((), (inputs, outputs ++ [s]))
myRead (i:nputs, outputs) = (i, (nputs, outputs))

bind :: MyIO a -> (a -> MyIO b) -> MyIO b
bind action1 action2 = \outsideworld ->
  let (action1Result, updatedWorld) = action1 outsideworld
  in action2 action1Result updatedWorld

program =
    myPrint "Please enter your name" `bind`
    \ignore -> myRead  `bind`
    \name -> myPrint ("Hello " ++ name)

main = print (program (["ariel"], []))