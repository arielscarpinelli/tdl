package clase6

object ADTBundledStatefulSecure extends App {

  case class Stack[T](push: T => Unit, pop: () => T, isEmpty: () => Boolean)

  def stack[T]():Stack[T] = {
    var content: List[T] = Nil

    def push(element:T) = {
      content = element :: content
    }

    def pop() = {
      content match {
        case head :: tail =>
          content = tail
          head
      }
    }

    def isEmpty() = content == Nil

    Stack(push, pop, isEmpty)
  }

  assert(stack().isEmpty())

  val s = stack[String]()

  s.push("ariel")
  s.push("alejandro")

  assert(!s.isEmpty())

  assert(s.pop() == "alejandro")
  assert(s.pop() == "ariel")
  assert(s.isEmpty())

}
