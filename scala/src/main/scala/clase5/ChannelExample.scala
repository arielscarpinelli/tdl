package clase5

import scala.concurrent.{Await, Channel, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ChannelExample extends App {

  def writer(channel: Channel[Int], n: Int, max:Int):Unit = {
    println("escribo " + n)
    channel.write(n)
    if(n < max) writer(channel, n+1, max)
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
    writer(channel, 1,100)
  }

  val runningWriter2 = Future {
    writer(channel, 100,200)
  }

  Await.result(runningWriter.zip(runningWriter2), Duration.Inf)

}
