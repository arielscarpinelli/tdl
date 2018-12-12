package clase3

import scala.io.StdIn

object Functor extends App {

  def readAnInt = {
    val str = StdIn.readLine("Enter an Int that you'd like to double:\n")

    try {
      Some(str.toInt)
    } catch {
      case ex: NumberFormatException => None
    }
  }

  val doubled = readAnInt.map(_ * 2)

  println(doubled)

  doubled match {
    case Some(x) => println("here is your double: " + x)
    case None => println("you are a mean person")
  }
}
