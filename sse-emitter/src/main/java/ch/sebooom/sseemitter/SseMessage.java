package ch.sebooom.sseemitter;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class SseMessage {


    private String from;


    private String message;

    public SseMessage(String from, String message) {

        this.from = from;
        this.message = message;
    }

    public SseMessage(){}
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*

    @Override
    public String toString() {


        return "Message{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
    */
}
