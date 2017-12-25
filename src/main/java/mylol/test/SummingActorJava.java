package mylol.test;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Random;

public class SummingActorJava extends AbstractActor {
    private static final Random rand = new Random();
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final Integer magicNumber;

    public SummingActorJava(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("SummingSystem");
        ActorRef actor = actorSystem.actorOf(Props.create(SummingActorJava.class, 100),"summing");
        for (Integer i = 0; i < 100; i++) {
            actor.tell(i, ActorRef.noSender());
        }
    }

    private Integer getRandom() {
        return rand.nextInt(10000);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Integer.class, i -> {
            Integer sleepTime = getRandom();
//            log.info(sleepTime.toString());

            Thread.sleep(sleepTime);

            log.info(sleepTime.toString(), magicNumber.toString(), i.toString());
            Integer result = magicNumber + i;
            log.info(result.toString());
        })
                .build();
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