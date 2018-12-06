package clase2

object StaticScoping extends App {

  val callee = (x: String) => {
    println("static $X")
  }

  val caller = (x: String) => {
    callee(x)
  }

  {
    val callee = (x: Int) => {
      println("dynamic $X")
    }
    caller("hola")
  }

  caller("hola")

}
