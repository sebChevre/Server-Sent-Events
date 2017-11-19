package ch.sebooom.sseemitter.emiter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class RandomDateEventEmiter implements Runnable{

    @Autowired
    ApplicationEventPublisher eventPublisher;

    private static final Logger LOGGER = Logger.getLogger(RandomDateEventEmiter.class);


    @Override
    public void run() {

        RandomDateObservable.get().observable().subscribe(dateEvent -> {
            LOGGER.info(String.format("Event consumed: %s",dateEvent));
            eventPublisher.publishEvent(dateEvent);
        });
    }
}
