package clase5

import scala.concurrent.{Await, Channel, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ChannelExample extends App {

  def producer(channel: Channel[Int], n: Int, max:Int):Unit = {
    println("escribo " + n)
    channel.write(n)
    if(n < max) producer(channel, n+1, max)
  }

  def consumer(channel: Channel[Int]) {
    println(channel.read)
    consumer(channel)
  }

  val channel = new Channel[Int]()

  Future {
    consumer(channel)
  }

  val runningProducer = Future {
    producer(channel, 1,100)
  }

  val runningProducer2 = Future {
    producer(channel, 100,200)
  }

  Await.result(runningProducer.zip(runningProducer2), Duration.Inf)

}
