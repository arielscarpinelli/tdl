package clase5

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Channel, Future}

object CalcServerPreActoresSincrono extends App {

  case class Calc(x: Int, sender: Channel[Result])

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


  val client = new Channel[Result]()

  println("client will send 4")
  server.write(Calc(4, client))

  val firstResult = client.read

  println("client will send 5")
  server.write(Calc(5, client))

  val secondResult = client.read

  println("final result:" + (firstResult.x + secondResult.x))

}


