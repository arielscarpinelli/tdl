module Monad where

import Control.Monad

-- class Monad m where
--   (>>=)  :: m a -> (  a -> m b) -> m b
--   return ::   a                 -> m a
--   fmap :: (a -> b) -> f a -> f b -- It is a functor!
--   join :: Monad m => m (m a) -> m a

imperative_ish = do
  print "Please enter your name"
  name <- getLine
  print ("Hello " ++ name)

functional =
  print "Please enter your name" >>=
  \_ -> getLine >>=
  \name -> print ("Hello " ++ name)

underneath =
  join (fmap (\greet -> print greet)
    (join (fmap (\name -> return ("Hello " ++ name))
      (join (fmap (\_ -> getLine) (print "Please enter your name"))))))

-- why "join"?
-- f = fmap (\_ -> getLine) (print "Please enter your name")
-- :t f
-- f :: IO (IO String)
-- :t join f
-- join f :: IO String

listsAreMonadsToo = do
  print ([1, 3, 5, 7] >>= \num -> [num, num+1])
  

main = underneath
