package clase1

object PatternMatching_Max extends App {
  val max = (list: List[Int]) => {

    def doMax(list: List[Int], acc: Int): Int = list match {
      case head :: tail => doMax(tail, if(head > acc) head else acc)
      case Nil => acc
    }

    doMax(list, Int.MinValue)

  }

  println(max(List(12, 3, 45, 22, 115, 4)))
  println(max(List()))

}