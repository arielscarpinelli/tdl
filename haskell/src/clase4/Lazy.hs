module Lazy where

lazy :: Int -> IO Int
lazy x = do
   print "en lazy"
   return (x + 14)
   
main = do
   let valor = lazy 2
   print "aca"
   valor
