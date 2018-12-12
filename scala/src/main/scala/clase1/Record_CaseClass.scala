package clase1

object Record_CaseClass extends App {
  case class Persona(nombre:String, apellido:String, edad:Int)

  val edad = 80
  val alumno = Persona("Roberto", "Sanchez", 80)

  println(alumno)
  println(alumno.nombre)
  println(alumno.edad)
  println(alumno.productArity)
  alumno.productIterator.foreach(println)

}
