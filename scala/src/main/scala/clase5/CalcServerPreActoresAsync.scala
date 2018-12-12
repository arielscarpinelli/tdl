package clase5

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Channel, Future}

object CalcServerPreActoresAsync extends App {

  case class Calc(x: Int, sender: Channel[Any])

  case class Work()

  case class Result(x: Int)

  def channelWithReader(f: Any => Unit): Channel[Any] = {
    val channel = new Channel[Any]()

    @tailrec
    def doRead(): Unit = {
      f(channel.read)
      doRead()
    }

    Future {
      doRead()
    }

    channel
  }

  val server = channelWithReader {
    case Calc(x, sender) =>
      println("server received: " + x)
      Thread.sleep(2000)
      sender.write(Result(x * x + 1))
  }

  var results = List[Int]()

  val client:Channel[Any] = channelWithReader {
    case Work() =>
      println("client will send 4")
      server.write(Calc(4, client))

      println("client will send 5")
      server.write(Calc(5, client))

      println("I can calculate a big factorial while waiting")
      Thread.sleep(5000)
      println("Now Im done with it")
    case Result(x) =>
      println("client received result: " + x)
      results ::= x
      if (results.size == 2) {
        println("final result:" + results.sum)
        System.exit(0)
      }

  }

  client.write(Work())

  Thread.sleep(1000000000)

}


