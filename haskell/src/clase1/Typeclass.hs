module Typeclass where

data Persona = Persona {
    nombre :: String,
    apellido :: String,
    edad :: Int
} deriving (Show)

alumno = Persona "Roberto" "Sanchez" 80
otro = Persona {nombre = "Sandro", apellido = "Gitano", edad = 80}

main = do
    print alumno
    print otro
    print (nombre alumno)
    print (apellido otro)
    print (edad alumno)

