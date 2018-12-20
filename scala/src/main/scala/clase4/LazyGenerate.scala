package clase4

object LazyGenerate extends App {

  //final case class ::[B](override val head: B, private[scala] var tl: List[B]) extends List[B] {
  //final class Cons[+A](hd: A, tl: => Stream[A]) extends Stream[A] {

  def generate(n:Int):Stream[Int] = {
    n #:: generate(n + 1)
  }

  println(generate(0).head)
  println(generate(0).tail.head)
  println(generate(0).tail.tail.head)
  println(generate(0).take(10).toList)

}
