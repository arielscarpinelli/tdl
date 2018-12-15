module Monad where

imperative_ish = do
  name <- getLine
  return ("Hello " ++ name)

functional =
  fmap (\name -> "Hello " ++ name) getLine

main = do
  print "Please enter your name"
  functional
