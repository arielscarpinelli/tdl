package clase7

import scala.io.AnsiColor

object Traits extends App {

  trait Shape {
    def area(): Double

    def perimeter(): Double
  }

  trait Styled {
    val color:String

    override def toString: String = color + getClass.getSimpleName + AnsiColor.RESET
  }

  trait RectShape extends Shape {
    protected def sides(): List[Double]

    def perimeter() = sides().sum
  }

  case class AnsiStyledTriangle(x: Double, y: Double, z: Double, color: String) extends RectShape with Styled {

    def sides() = List(x, y, z)

    def area() = {
      // Heron's formula
      val p = perimeter() / 2
      Math.sqrt(p * (p - x) * (p - y) * (p - z))
    }

  }

  case class Rectagle(width: Double, height: Double) extends RectShape {
    def sides() = List(width, height, width, height)

    def area(): Double = width * height
  }

  case class AnsiStyledCircle(radius: Double, color: String) extends Shape with Styled {
    def area(): Double = Math.PI * radius * radius

    def perimeter(): Double = Math.PI * 2 * radius
  }


  val shapes = List(AnsiStyledTriangle(3, 4, 5, AnsiColor.RED), Rectagle(2, 3), AnsiStyledCircle(3, AnsiColor.BLUE))

  println("areas: " + shapes.map(_.area()))

  println("perimeters: " + shapes.map(_.perimeter()))

  println("styled " + shapes)

}
