import Messages.{Done, GiveMeRandomNumber, Start}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.util.Random._

object Messages {

  case class Done(randomNumber: Int)

  case object GiveMeRandomNumber

  case class Start(actorRef: ActorRef)

}

class RandomNumberGeneratorActor extends Actor {
  override def receive: Receive = {
    case GiveMeRandomNumber => println("received a message to generate a random integer")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
  }
}

class QueryActor extends Actor {
  override def receive: Receive = {
    case Start(actorRef) => println(s"send me the next random number")
      actorRef ! GiveMeRandomNumber
    case Done(randomNumber) => println(s"recived a random number $randomNumber")
  }
}

object Communication extends App {
  val actorSystem = ActorSystem("HelloAkka")
  val randomNumberGenerator = actorSystem.actorOf(Props[RandomNumberGeneratorActor], "rundomNumberGeneratorActor")
  val queryActor = actorSystem.actorOf(Props[QueryActor], "queryActor")
  queryActor ! Start(randomNumberGenerator)
  println("end")
}