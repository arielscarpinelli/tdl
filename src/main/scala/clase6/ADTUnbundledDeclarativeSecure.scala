package clase6

object ADTUnbundledDeclarativeSecure extends App {

  private case class Key()

  case class Wrapper[T](private val content: List[T]) {
    def unwrap(k: Key): List[T] = content
  }

  def stack[T](): Wrapper[T] = Wrapper[T](Nil)

  def push[T](stack: Wrapper[T], element: T) = Wrapper[T](element :: stack.unwrap(Key()))

  def pop[T](s: Wrapper[T]): (T, Wrapper[T]) = {
    s.unwrap(Key()) match {
      case head :: tail => (head, Wrapper[T](tail))
    }
  }

  def isEmpty[T](s: Wrapper[T]) = s.unwrap(Key()) == Nil

  assert(isEmpty(stack()))

  val s = push(push(stack(), "ariel"), "alejandro")

  assert(!isEmpty(s))

  assert(pop(s)._1 == "alejandro")
  assert(pop(pop(s)._2)._1 == "ariel")
  assert(isEmpty(pop(pop(s)._2)._2))

}
