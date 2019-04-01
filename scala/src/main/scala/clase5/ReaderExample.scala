package clase5

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Channel, Future}

object ReaderExample extends App {

  def channelWithReader[T](f: T => Unit) = {
    val channel = new Channel[T]()

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

  val channel = channelWithReader[String](println)

  channel.write("hola")
  channel.write("chau")

  Thread.sleep(1000)

}


