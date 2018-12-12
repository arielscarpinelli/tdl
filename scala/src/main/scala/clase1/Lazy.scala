package clase1

object Lazy extends App {

  //final case class ::[B](override val head: B, private[scala] var tl: List[B]) extends List[B] {
  //final class Cons[+A](hd: A, tl: => Stream[A]) extends Stream[A] {

  def ints(n:Int):Stream[Int] = {
    n #:: ints(n + 1)
  }

  println(ints(0).head)
  println(ints(0).tail.head)
  println(ints(0).tail.tail.head)
  println(ints(0).take(10).toList)

}
