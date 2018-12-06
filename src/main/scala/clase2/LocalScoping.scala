package clase2

object LocalScoping extends App {
  val X = 1

  {
    val X = 2
    println(X)
  }

  println(X)

}
