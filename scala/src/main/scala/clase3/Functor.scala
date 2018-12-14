package clase3

import scala.io.StdIn

object Functor extends App {

  def readAnInt(message:String) = {
    val str = StdIn.readLine(message)

    try {
      Some(str.toInt)
    } catch {
      case ex: NumberFormatException => None
    }
  }

  val maybeInt = readAnInt("Enter an Int that you'd like to double:\n")
  val maybeDoubled = maybeInt.map(_ * 2)

  println(maybeDoubled)

  maybeDoubled match {
    case Some(x) => println("here is your double: " + x)
    case None => println("you are a mean person")
  }
}
