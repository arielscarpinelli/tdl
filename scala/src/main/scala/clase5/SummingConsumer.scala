package clase5

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Channel, Future}

object SummingConsumer extends App {

  def producer(channel: Channel[Int], n: Int, max:Int):Unit = {
    println("escribo " + n)
    channel.write(n)
    if(n < max) producer(channel, n+1, max)
  }

  def consumer[T, A](initialAcc: A)(f: (T, A) => A) = {
    val channel = new Channel[T]()

    @tailrec
    def doConsume(acc: A): Unit = {
      val updatedAcc = f(channel.read, acc)
      doConsume(updatedAcc)
    }

    Future {
      doConsume(initialAcc)
    }

    channel
  }

  val channel = consumer(0) {(acc:Int, x) =>
    println(acc)
    acc + x
  }

  val runningProducer = Future {
    producer(channel, 1,100)
  }

  val runningProducer2 = Future {
    producer(channel, 100,200)
  }

  Await.result(runningProducer.zip(runningProducer2), Duration.Inf)

}
