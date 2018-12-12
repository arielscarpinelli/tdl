package clase5

import scala.concurrent.{Await, Channel, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ChannelExample extends App {

  def writer(channel: Channel[Int], max:Int) = {

    def doWrite(n:Int):Unit = {
      if (n < max) {
        println("escribo " + n)
        channel.write(n)
        doWrite(n + 1)
      }
    }

    doWrite(0)

  }

  def reader(channel: Channel[Int]) {
    println(channel.read)
    reader(channel)
  }

  val channel = new Channel[Int]()

  Future {
    reader(channel)
  }

  val runningWriter = Future {
    writer(channel, 100)
  }

  Await.result(runningWriter, Duration.Inf)

}
