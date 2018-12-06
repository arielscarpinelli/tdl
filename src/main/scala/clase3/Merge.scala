package clase3

object Merge extends App {

  def merge(left: List[Int], right: List[Int]): List[Int] = (left, right) match {
    case (Nil, _) => right
    case (_, Nil) => left
    case (l :: eft, r :: ight) =>
      if (l < r)
        l :: merge(eft, right)
      else
        r :: merge(left, ight)
  }

  val l1 = List(10, 20, 30, 40, 50, 60)
  val l2 = List(2, 4, 5, 7, 13, 14, 22, 45, 60, 100, 150)
  val l3 = merge(l1, l2)

  println(l3)

}
