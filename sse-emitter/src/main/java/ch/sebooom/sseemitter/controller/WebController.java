package ch.sebooom.sseemitter.controller;

import ch.sebooom.sseemitter.SSEEmeteur;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@Controller
public class WebController {

    private static final Logger LOGGER = Logger.getLogger(WebController.class);

    @Autowired
    List<SSEEmeteur> emeteurs;


    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream(HttpServletRequest request) throws IOException {

        LOGGER.info("/stream call received from client: " + request.getRemoteAddr());

        SSEEmeteur emeteur = new SSEEmeteur(request.getRemoteAddr());

        emeteurs.add(emeteur);

        LOGGER.info("new emitter added");

        emeteur.onError(error->{
            LOGGER.error("Error happened with the emiiter: " + emeteur.getIpAdresse());
        });

        emeteur.onTimeout(()->{

        });

        emeteur.onCompletion(() -> {
            emeteurs.remove(emeteur);
            LOGGER.info("Emteur complete: " + emeteur.getIpAdresse());
        });

        return emeteur;
    }

}
