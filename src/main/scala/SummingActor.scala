import akka.actor.{Actor, ActorSystem, Props}

class SummingActorWithConstructor(initialSum: Int) extends Actor {
  var sum = 0

  override def receive: Receive = {
    case x: Int => sum += initialSum + x
      println(s"my state as sum is $sum")
    case _ => println("I don't know what are you talking about")
  }
}

object BehaviourAndState extends App {
  val actorSystem = ActorSystem("HelloAkka")
  var actor = actorSystem.actorOf(Props(classOf[SummingActorWithConstructor], 10), "summingActor")

  println(actor.path)
}