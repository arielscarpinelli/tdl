package clase3

import scala.io.StdIn
import scala.util.Try

object Applicative extends App {

  def applicative[T, U](f: (T, T) => U, left:Option[T], right:Option[T]): Option[U] = {
    (left, right) match {
      case (Some(x), Some(y)) => Try(f(x, y)).toOption
      case _ => None
    }
  }


  val leftOperand = Try(StdIn.readLine("Enter an Int:\n").toInt).toOption
  val rightOperand = Try(StdIn.readLine("Enter other Int:\n").toInt).toOption

  println(applicative((x:Int, y:Int) => x + y, leftOperand, rightOperand))
  println(applicative((x:Int, y:Int) => x - y, leftOperand, rightOperand))
  println(applicative((x:Int, y:Int) => x * y, leftOperand, rightOperand))
  println(applicative((x:Int, y:Int) => x / y, leftOperand, rightOperand))

}
