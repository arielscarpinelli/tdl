package clase6

object ADTBundledStatefulOpen extends App {

  case class StackContent[T](var content: List[T])
  case class Stack[T](push: T => Unit, pop: () => T, isEmpty: () => Boolean, content: StackContent[T])

  def stack[T]():Stack[T] = {
    val content = StackContent[T](Nil)

    def push(element:T) = {
      content.content = element :: content.content
    }

    def pop() = {
      content.content match {
        case head :: tail =>
          content.content = tail
          head
      }
    }

    def isEmpty() = content.content == Nil

    Stack(push, pop, isEmpty, content)
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
