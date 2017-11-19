package ch.sebooom.sseemitter.emiter;

import ch.sebooom.sseemitter.event.TickSecondEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SecondTickEmitter {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    private int tickNumber = 0;

    private static final Logger LOGGER = Logger.getLogger(SecondTickEmitter.class);

    @Scheduled(fixedRate = 1000)
    public void secondTick() {
        tickNumber++;
        LOGGER.info(String.format("Tick emited %d",tickNumber));
        eventPublisher.publishEvent(new TickSecondEvent(tickNumber));
    }


}
