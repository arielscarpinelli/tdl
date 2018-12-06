package clase6

object ADTUnbundledStatefulOpen extends App {

  case class Stack[T](var content: List[T])

  def stack[T](): Stack[T] = Stack[T](Nil)

  def push[T](s: Stack[T], element: T) = {
    s.content = element :: s.content
  }

  def pop[T](s: Stack[T]) = {
    s.content match {
      case head :: tail =>
        s.content = tail
        head
    }
  }

  def isEmpty[T](s: Stack[T]) = s.content == Nil

  assert(isEmpty(stack()))

  val s = stack[String]()

  push(s, "ariel")
  push(s, "alejandro")

  assert(!isEmpty(s))

  assert(pop(s) == "alejandro")
  assert(pop(s) == "ariel")
  assert(isEmpty(s))

}
