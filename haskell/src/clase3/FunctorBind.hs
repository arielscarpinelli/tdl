module FunctorBind where

import Text.Read

readInt :: String -> Maybe Int
readInt line = readMaybe line :: Maybe Int

-- maptiply fx fy = fmap (\x -> fmap (\y -> x * y) fy) fx doesn't work!
maptiply fx fy = fx >>= (\x -> fmap (\y -> x * y) fy)

desc (Just x) = "your double is " ++ (show x)
desc (Nothing) = "you are a mean person"

main = do
  print "Enter a number"
  line <- getLine
  print "Enter a another"
  line2 <- getLine
  print (
    let maybeResult = maptiply (readInt line) (readInt line2)
    in (show maybeResult) ++ " -> " ++ (desc maybeResult))
