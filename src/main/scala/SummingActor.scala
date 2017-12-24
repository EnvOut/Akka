import akka.actor.{Actor, ActorSystem, Props}

class SummingActor extends Actor {
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum += x
      println(s"my state as sum is $sum")
    case _ => println("I don't know what are you talking about")
  }
}

object BehaviourAndState extends App {
  val actorSystem = ActorSystem("HelloAkka")
  var actor = actorSystem.actorOf(Props[SummingActor])
  var actor2 = actorSystem.actorOf(Props[SummingActor], "actorName")
  println(actor.path)
  println(actor2.path)
}