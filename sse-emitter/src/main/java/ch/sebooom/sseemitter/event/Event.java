package ch.sebooom.sseemitter.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Event {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime date;
    protected String eventId;

    public Event (String eventId) {
        this.date = LocalDateTime.now();
        this.eventId = eventId;
    }

    public String id() {
        return eventId;
    }

    public abstract String eventName ();

}
