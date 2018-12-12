package clase6

object ADTUnbundledDeclarativeOpen extends App {

  type Stack[T] = List[T]

  def stack[T](): Stack[T] = Nil

  def push[T](stack: Stack[T], element: T) = element :: stack

  def pop[T](s: Stack[T]): (T, Stack[T]) = {
    s match {
      case head :: tail => (head, tail)
    }
  }

  def isEmpty[T](s: Stack[T]) = s == Nil

  assert(isEmpty(stack()))

  val s = push(push(stack(), "ariel"), "alejandro")

  assert(!isEmpty(s))

  assert(pop(s)._1 == "alejandro")
  assert(pop(pop(s)._2)._1 == "ariel")
  assert(isEmpty(pop(pop(s)._2)._2))


}
