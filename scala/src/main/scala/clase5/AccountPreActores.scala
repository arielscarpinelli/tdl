package clase5

import scala.annotation.tailrec
import scala.concurrent.{Channel, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import clase5.SummingConsumer.consumer

object AccountPreActores extends App {

  trait AccountOp
  case class Deposit(amount:Int) extends AccountOp
  case class Extract(amount:Int) extends AccountOp

  val cuenta = consumer[AccountOp, Int](0) { (m, balance) => m match {
    case Deposit(x) => balance + x
    case Extract(x) => if (balance >= x) {
      println(balance - x)
      balance - x
    } else {
      println("ignoring")
      println(balance)
      balance
    }
  }}

  cuenta.write(Deposit(50))
  Future {
    cuenta.write(Extract(35))
  }
  Future {
    cuenta.write(Extract(35))
  }

  Thread.sleep(1000)
}
