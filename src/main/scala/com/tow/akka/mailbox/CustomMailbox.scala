package com.tow.akka.mailbox

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object CustomMailbox extends App {
  val actorSystem = ActorSystem("HelloAkka")

  val actor = actorSystem.actorOf(Props[MySpecialActor].withDispatcher("custom-dispatcher"))

  val actor1 = actorSystem.actorOf(Props[MyActor], "xyz")
  var actor2 = actorSystem.actorOf(Props[MyActor], "MyActor")

  actor1 ! ("hello", actor)
  actor2 ! ("hello", actor)
}


class MyActor extends Actor {
  override def receive = {
    case (msg: String, actorRef: ActorRef) => actorRef ! msg
    case msg => println(msg)
  }
}

class MySpecialActor extends Actor {
  override def receive = {
    case msg: String => println(s"msg $msg")
  }
}