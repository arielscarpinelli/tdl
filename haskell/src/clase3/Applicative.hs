module Applicative where

import Text.Read

sumStrings :: String -> String -> Maybe Int
sumStrings line secondLine = 
  let
    firstNumber = readMaybe line :: Maybe Int
    secondNumber = readMaybe secondLine :: Maybe Int
    in
      Just (+) <*> firstNumber <*> secondNumber

main = do
  print "enter a number"
  line <- getLine
  print "enter another number"
  secondLine <- getLine
  print (sumStrings line secondLine)
  print "lists are applicatives too"
  print ([(+), (*)] <*> [1,2] <*> [3,4])
