package ch.sebooom.sseemitter;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SSEEmeteur extends SseEmitter {

    private String ipAdresse;

    public SSEEmeteur(String ip){
        super();
        this.ipAdresse = ip;
    }

    public String getIpAdresse() {
        return ipAdresse;
    }

    @Override
    public String toString() {
        return "SSEEmeteur{" +
                "ipAdresse='" + ipAdresse + '\'' +
                '}';
    }
}
