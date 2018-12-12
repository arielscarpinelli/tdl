package clase1

object Mayor extends App {

  val mayor = (x:Int, y:Int) => {
    if (x > y) x else y
  }

  val a = 30
  val b = 20
  val m = mayor(a, b)

  print(m)

}
