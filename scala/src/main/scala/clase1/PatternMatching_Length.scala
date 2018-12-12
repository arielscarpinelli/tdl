package clase1

object PatternMatching_Length extends App {
  val length = (list: List[Int]) => {

    def doLength(list: List[Int], acc: Int): Int = list match {
      case head :: tail => doLength(tail, acc + 1)
      case Nil => acc
    }

    doLength(list, 0)

  }

  println(length(List(1, 2, 3)))
  println(length(List(1)))
  println(length(List()))

}