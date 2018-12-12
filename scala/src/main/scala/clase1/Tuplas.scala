package clase1

object Tuplas extends App {
  val tupla = (3, "x", "a", "b")
  println(tupla)
  println(tupla._1)
  println(tupla.productArity)
  tupla.productIterator.foreach(println)
}
