package clase5

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}


object AccountWorker extends App {

  trait AccountOp
  case class Deposit(amount: Int) extends AccountOp
  case class GenerateStatement() extends AccountOp

  case class DoGeneratePDF(respondTo: ActorRef) extends AccountOp


  class Account extends Actor {

    var balance: Int = 0

    def receive = {
      case Deposit(x) => {
        balance += x
        sender() ! "balance deposited"
      }
      case GenerateStatement() => {
        val child = context.actorOf(Props[Worker], "worker")
        child ! DoGeneratePDF(sender())
      }
    }
  }

  class Worker extends Actor {
    def receive = {
      case DoGeneratePDF(respondTo) => {
        Thread.sleep(5000)
        respondTo ! "statement PDF"
        // our job here is done, kill ourselves
        self ! PoisonPill
      }
    }
  }

  class Client(account:ActorRef) extends Actor {

    def receive = {
      case "start" => {
        account ! GenerateStatement()
        account ! Deposit(50)
      }
      case x:String => println(x)
    }
  }
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val account = system.actorOf(Props[Account], name = "account")
  val client = system.actorOf(Props(classOf[Client], account))

  client ! "start"


}