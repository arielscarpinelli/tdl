module Mutable where

import Data.IORef

main = do
    box <- newIORef 4
    readIORef box >>= print
    modifyIORef box (2*)
    readIORef box >>= print
    writeIORef box 0
    readIORef box >>= print



