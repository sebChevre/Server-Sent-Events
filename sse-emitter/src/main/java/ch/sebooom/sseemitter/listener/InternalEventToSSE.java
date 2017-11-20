package ch.sebooom.sseemitter.listener;

import ch.sebooom.sseemitter.SSEEmeteur;
import ch.sebooom.sseemitter.controller.WebController;
import ch.sebooom.sseemitter.event.DateEvent;
import ch.sebooom.sseemitter.event.Event;
import ch.sebooom.sseemitter.event.TickSecondEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InternalEventToSSE {

    private static final Logger LOGGER = Logger.getLogger(InternalEventToSSE.class);

    @Autowired
    List<SSEEmeteur> emeteurs;

    @EventListener
    public void listenEvenDate(DateEvent event){
        SseEmitter.SseEventBuilder builder = SseEmitter.event()
                .data(event)
                .id(event.id())
                .name(event.eventName())
                .reconnectTime(10_000L);

        emitSseEvent(builder);
    }

    @EventListener
    public void listenTickEvent(TickSecondEvent event){
        SseEmitter.SseEventBuilder builder = SseEmitter.event()
                .data(event)
                .id(event.id())
                .reconnectTime(10_000L);
        emitSseEvent(builder);
    }




    private void emitSseEvent (SseEmitter.SseEventBuilder event) {

        List<SSEEmeteur> deadEmitters = new ArrayList<>();

        LOGGER.info("SSE Emiter emit event");

        logActiveEmeteurs();

        emeteurs.forEach((SSEEmeteur emitter) -> {
            try {

                emitter.send(event);

                LOGGER.info("Sending" + event.toString());
            } catch (IOException e) {

                LOGGER.info("Emitter dead: " + emitter.toString());
                deadEmitters.add(emitter);

            }
        });

        emeteurs.remove(deadEmitters);
    }

    private void logActiveEmeteurs() {

        StringBuilder emetteursActifs = new StringBuilder();

        emetteursActifs.append("Registered emetteurs: ");

        emeteurs.forEach(emiter->{
            emetteursActifs.append(emiter);
        });

        LOGGER.info(emetteursActifs);
    }


}
