package clase6

object ADTBundledDeclarativeOpen extends App {

  case class Stack[T](push: T => Stack[T], pop: () => (T, Stack[T]), isEmpty: () => Boolean, content: List[T])

  def stack[T]():Stack[T] = {

    def stackOps(content: List[T]): Stack[T] = {
      def push(element:T) = {
        stackOps(element :: content)
      }

      def pop() = {
        content match {
          case head :: tail =>
            (head, stackOps(tail))
        }
      }

      def isEmpty() = content == Nil

      Stack(push, pop, isEmpty, content)

    }

    stackOps(Nil)
  }

  assert(stack().isEmpty())

  val s = stack()
    .push("ariel")
    .push("alejandro")

  assert(!s.isEmpty())

  assert(s.pop()._1 == "alejandro")
  assert(s.pop()._2.pop()._1 == "ariel")
  assert(s.pop()._2.pop()._2.isEmpty())

}
