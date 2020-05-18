package clase5

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object BadAccount extends App {

  object Account {
    var balance:Int = 0
    def deposit(amount:Int) {
      balance += amount
    }
    def extract(amount:Int) {
      println(balance)
      if(balance > amount) {
        Thread.sleep(100)
        balance -= amount
      } else {
        print("ignoring")
      }
      println(balance)
    }
  }
  Account.deposit(50)
  Future { Account.extract(35) }
  Future { Account.extract(35) }

  Thread.sleep(1000)
}
