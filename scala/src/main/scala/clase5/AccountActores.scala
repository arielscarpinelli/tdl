package clase5

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


object AccountActores extends App {

  trait AccountOp
  case class Deposit(amount:Int) extends AccountOp
  case class Extract(amount:Int) extends AccountOp

  class Account extends Actor {

    var balance: Int = 0

    def receive = {
      case Deposit(x) => {
        balance += x
        println(balance)
      }
      case Extract(x) => {
        if (balance >= x) {
          balance -= x
        } else {
          println("ignoring")
        }
        println(balance)
      }
    }
  }


  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val account = system.actorOf(Props[Account], name = "account")

  account ! Deposit(50)
  account ! Extract(35)
  account ! Extract(35)
}