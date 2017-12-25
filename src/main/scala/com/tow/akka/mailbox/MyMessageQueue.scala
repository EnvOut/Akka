package com.tow.akka.mailbox

import java.util.concurrent.ConcurrentLinkedQueue

import akka.actor.ActorRef
import akka.dispatch.{Envelope, MessageQueue}

class MyMessageQueue extends MessageQueue {
  private final val queue = new ConcurrentLinkedQueue[Envelope]()

  override def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
    if (handle.sender.path.name == "MyActor") {
      handle.sender ! "Hey dude, How are you?, I know your name, processing your request"
      queue.offer(handle)
    } else {
      handle.sender ! "I don't talk to straingers, I can't process your request"
    }
  }

  override def dequeue(): Envelope = {
    queue.poll
  }

  override def numberOfMessages: Int = {
    queue.size
  }

  override def hasMessages: Boolean = {
    !queue.isEmpty
  }

  override def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
    while (hasMessages) {
      deadLetters.enqueue(owner, dequeue())
    }
  }
}
