package clase5

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


object CalcServerActores extends App {

  case class Calc(x: Int)

  case class Request()

  case class Result(x: Int)

  class Server extends Actor {
    def receive = {
      case Calc(x) =>
        println("server received: " + x)
        Thread.sleep(2000)
        sender() ! Result(x * x + 1)
    }
  }

  class Client(server: ActorRef) extends Actor {

    private var results = List[Int]()

    def receive = {
      case Request() =>
        println("client will send 4")
        server ! Calc(4)

        println("client will send 5")
        server ! Calc(5)
      case Result(x) =>
        println("client received result: " + x)
        results ::= x
        if (results.size == 2) {
          println("final result:" + results.sum)
        }
    }
  }


  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val server = system.actorOf(Props[Server], name = "server")
  val client = system.actorOf(Props(classOf[Client], server), name = "client")

  client ! Request()
}