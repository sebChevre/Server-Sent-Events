package ch.sebooom.sseemitter.event;

public class TickSecondEvent extends Event{

    private int tick;

    @Override
    public String toString() {
        return "TickSecondEvent{" +
                "date=" + date +
                ", eventId='" + eventId +
                ",tick='" + tick + '\'' +
                '}';
    }

    public TickSecondEvent (int tickNumber) {
        super();
        this.tick = tickNumber;
    }

    @Override
    public String eventName() {
        return "tickEvent";
    }
}
