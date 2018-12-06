package clase1

object PatternMatching_Abs extends App {

  val absInt = (x: Int) => if (x < 0) -x else x

  def abs(list: List[Int]): List[Int] = list match {
    case head :: tail => absInt(head) :: abs(tail)
    case _ => list
  }

  println(abs(List(22, -14, 13, -1, -2, 4)))
  println(abs(List()))

}