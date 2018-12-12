module StaticScoping where

callee x = print ("static " ++ x)

caller x = callee x

main = do
    let callee x = print ("dynamic " ++ x)
    caller "hola"


