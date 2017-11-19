package ch.sebooom.sseemitter.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Objet encapsulant un événement générant une date
 * au moment de l'ainstanciation
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DateEvent extends Event{

    @Override
    public String toString() {
        return "DateEvent{" +
                "date=" + date +
                ", eventId='" + eventId + '\'' +
                '}';
    }

    public DateEvent() {
        super();
    }

    @Override
    public String eventName() {
        return "dateEvent";
    }


}
