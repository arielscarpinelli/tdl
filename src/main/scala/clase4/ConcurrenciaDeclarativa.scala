package clase4

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ConcurrenciaDeclarativa extends App {

  val future1 = Future {
    Thread.sleep(200)
    4
  }

  val future2 = Future {
    1 + Await.result(future1, Duration.Inf)
  }

  val future3 = Future {
    Thread.sleep(100)
    2
  }

  val future4 = Future {
    Await.result(future2, Duration.Inf) + Await.result(future3, Duration.Inf)
  }

  val future4Fluent = future2.zip(future3)
      .map(result => result._1 + result._2)

  future1.foreach(x => println("future1 " + x))
  future2.foreach(x => println("future2 " + x))
  future3.foreach(x => println("future3 " + x))
  future4.foreach(x => println("future4 " + x))
  future4Fluent.foreach(x => println("future4Fluent " + x))

  Await.result(future4, Duration.Inf)

}
