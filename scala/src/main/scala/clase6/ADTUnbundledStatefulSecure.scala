package clase6

object ADTUnbundledStatefulSecure extends App {

  private case class Key()

  case class Stack[T](private var content:List[T]) {
    def unwrap(k:Key):List[T] = content
    def wrap(k:Key, content:List[T]):Unit = {
      this.content = content
    }
  }

  def stack[T](): Stack[T] = Stack[T](Nil)

  def push[T](s: Stack[T], element: T) = {
    s.wrap(Key(), element :: s.unwrap(Key()))
  }

  def pop[T](s: Stack[T]) = {
    s.unwrap(Key()) match {
      case head :: tail =>
        s.wrap(Key(), tail)
        head
    }
  }

  def isEmpty[T](s: Stack[T]) = s.unwrap(Key()) == Nil

  assert(isEmpty(stack()))

  val s = stack[String]()

  push(s, "ariel")
  push(s, "alejandro")

  assert(!isEmpty(s))

  assert(pop(s) == "alejandro")
  assert(pop(s) == "ariel")
  assert(isEmpty(s))

}
