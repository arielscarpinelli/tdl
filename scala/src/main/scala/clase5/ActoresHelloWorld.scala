package clase5

import akka.actor.{Actor, ActorSystem, Props}

object ActoresHelloWorld extends App {

  class Greeter extends Actor {
    def receive = {
      case name:String =>
        println("hello " + name)
    }
  }

  val system = ActorSystem("HelloSystem")
  val greeter = system.actorOf(Props[Greeter], name = "greeter")

  greeter ! "ariel"
}