package clase3

object Split extends App {

  def split(list: List[Int]): (List[Int], List[Int]) = {

    def doSplit(list: List[Int], accL: List[Int], accR: List[Int]): (List[Int], List[Int]) = list match {
      case x :: y :: rest => doSplit(rest, accL :+ x, accR :+ y)
      case x :: Nil => (accL :+ x, accR)
      case _ => (accL, accR)
    }

    doSplit(list, Nil, Nil)

  }

  val l1 = List(10, 20, 30, 40, 50, 60, 70, 80, 90)
  val (l2, l3) = split(l1)

  println(l2)
  println(l3)

}
