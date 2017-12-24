import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class FibbonachiActor extends Actor {
  override def receive: Receive = {
    case num: Int => val fibbonachiNumber = fib(num)
      sender ! fibbonachiNumber
  }

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n - 1) + fib(n - 2)
  }
}

object FibbonachiActorApp extends App {
  implicit val timeout: Timeout = Timeout(10 seconds)
  val actorSystem = ActorSystem("HelloAkka")
  val actor = actorSystem.actorOf(Props[FibbonachiActor])

  val future = (actor ? 10).mapTo[Int]
  val fibbonachiNumber = Await.result(future, 10 seconds)
  println(fibbonachiNumber)
}