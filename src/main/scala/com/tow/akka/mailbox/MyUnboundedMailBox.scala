package com.tow.akka.mailbox

import akka.actor.{ActorRef, ActorSystem}
import akka.dispatch.{MailboxType, MessageQueue, ProducesMessageQueue}
import com.typesafe.config.Config

class MyUnboundedMailBox extends MailboxType with ProducesMessageQueue[MyMessageQueue] {
  def this(settings: ActorSystem.Settings, config: Config) = {
    this()
  }

  override def create(owner: Option[ActorRef], system: Option[ActorSystem]): MessageQueue = new MyMessageQueue()
}