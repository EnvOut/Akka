package mylol.test;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SummingActorJava extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private final Integer magicNumber;

    public SummingActorJava(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }

    public static void main(String[] args) {
        /**
         *  val actorSystem = ActorSystem("HelloAkka")
         var actor = actorSystem.actorOf(Props(classOf[SummingActorWithConstructor], 10), "summingActor")

         while (true ){
         Thread.sleep(3000)
         actor ! 1
         }

         println(actor.path)
         */
        ActorSystem actorSystem = ActorSystem.create("SummingSystem");
        ActorRef actor = actorSystem.actorOf(Props.create(SummingActorJava.class,100));

        actor.tell(1,ActorRef.noSender());
    }
}
/**
 * public class SomeOtherActor extends AbstractActor {
 * // Props(new DemoActor(42)) would not be safe
 * ActorRef demoActor = getContext().actorOf(DemoActor.props(42), "demo");
 * // ...
 * }
 * }
 **/