package clase1

object Listas extends App {

  val l1 = List(1, 2, 3)
  val l2 = 1 :: 2 :: 3 :: Nil
  val l3 = ::(1, ::(2, ::(3, Nil)))

  println(l1)
  println(l1.head)
  println(l1.tail)

  println(l1.tail.head)
  println(l1.tail.tail.tail)

  println(l2)
  println(l3)
  println(l1 == l2)
  println(l2 == l3)

}
