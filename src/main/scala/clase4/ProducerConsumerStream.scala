package clase4

object ProducerConsumerStream extends App {

  //final class Cons[+A](hd: A, tl: => Stream[A]) extends Stream[A] {
  // `tail` is by name / "lazy"

  def producer(max:Int): Stream[Int] = {

    def doProduce(n:Int): Stream[Int] = {
      if (n < max) {
        println("produzco " + n)
        n #:: doProduce(n + 1)
      }
      else
        Stream.empty
    }

    doProduce(0)

  }

  def consumer(what: Stream[Int]) {
    for(n <- what if (n % 2) == 0) {
      println(n)
    }
  }

  consumer(producer(50))

}
