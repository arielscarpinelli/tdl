package clase4

object Lazy extends App {

  lazy val lazy1 = {
    println("en 1")
    125
  }

  lazy val lazy2 = {
    println("en 2")
    14
  }

  lazy val lazy3 = lazy1 + lazy2

  println("hola")
  println(lazy2)
  println(lazy3)

}