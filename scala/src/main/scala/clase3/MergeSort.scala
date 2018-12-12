package clase3

object MergeSort extends App {

  def merge(left: List[Int], right: List[Int]): List[Int] = (left, right) match {
    case (Nil, _) => right
    case (_, Nil) => left
    case (l :: eft, r :: ight) =>
      if (l < r)
        l :: merge(eft, right)
      else
        r :: merge(left, ight)
  }

  def split(list: List[Int]): (List[Int], List[Int]) = {

    def doSplit(list: List[Int], accL: List[Int], accR: List[Int]): (List[Int], List[Int]) = list match {
      case x :: y :: rest => doSplit(rest, accL :+ x, accR :+ y)
      case x :: Nil => (accL :+ x, accR)
      case _ => (accL, accR)
    }

    doSplit(list, Nil, Nil)

  }

  def mergeSort(list:List[Int]): List[Int] = list match {
    case Nil => Nil
    case x :: Nil => List(x)
    case _ => {
      val (xs, ys) = split(list)
      merge(mergeSort(xs), mergeSort(ys))
    }
  }

  val list = List(10, 5, 30, 33, 3, 54, 40, 22, 5054, 200, 1000, 51)
  println(mergeSort(list))

}
