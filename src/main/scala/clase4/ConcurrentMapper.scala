package clase4

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object ConcurrentMapper extends App {

  def futureMap(list:List[Int], f: Int => Int): List[Future[Int]] = list match {

    case x :: xs => Future { f(x) } :: futureMap(xs, f)
    case Nil => Nil

  }

  def map(list:List[Int], f: Int => Int) = {
    val eventualInts = futureMap(list, f)

    // for showing them resolving!
    eventualInts.foreach(_.foreach(println))

    Await.result(Future.sequence(eventualInts), Duration.Inf)
  }

  val l = List(1,2,3,4,5)
  println(map(l, _ * 2))

}
