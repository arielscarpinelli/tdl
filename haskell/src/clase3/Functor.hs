module Functor where

import Text.Read

readInt :: String -> Maybe Int
readInt line = readMaybe line :: Maybe Int

mapDouble = fmap (\int -> int * 2)

desc (Just x) = "your double is " ++ (show x)
desc (Nothing) = "you are a mean person"

main = do
  print "Enter a number to double"
  line <- getLine
  print (
    let maybeDouble = mapDouble (readInt line)
    in (show maybeDouble) ++ " -> " ++ (desc maybeDouble))
  print "lists are functors too"
  print ((2*) <$> [1, 2, 3, 4])
