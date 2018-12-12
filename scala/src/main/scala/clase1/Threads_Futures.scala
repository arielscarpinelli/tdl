package clase1

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Threads_Futures extends App {

  val a = Future {
    Thread.sleep(2000)
    10
  }

  val b = Future {
    4
  }

  val c = a.zip(b)
    .map { resolved => resolved._1 + resolved._2 }

  // Sleep main thread until complete
  val result = Await.result(c, Duration.Inf)
  println(result)

}
