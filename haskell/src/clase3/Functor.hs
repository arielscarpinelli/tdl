module Functor where

import Text.Read

toDouble :: String -> Maybe Int
toDouble line = 
  let
    maybeInt = readMaybe line :: Maybe Int
    maybeDouble = fmap (\int -> int * 2) maybeInt
    in maybeDouble

desc (Just x) = "your double is " ++ (show x)
desc (Nothing) = "you are a mean person"

main = do
  print "Enter a number to double"
  line <- getLine
  print (
    let maybeDouble = toDouble line
    in (show maybeDouble) ++ " -> " ++ (desc maybeDouble))
  print "lists are functors too"
  print ((2*) <$> [1, 2, 3, 4])
