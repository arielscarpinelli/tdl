package clase3

import scala.io.StdIn
import scala.util.Try

object Monad extends App {

  val dividend = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
  val divisor = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)

  val weird = dividend.map(x => divisor.map(y => x/y))

  println(weird)

  val monadic = dividend.flatMap(x => divisor.map(y => x/y))

  println(monadic)

}
