package ch.sebooom.sseemitter.emiter;


import ch.sebooom.sseemitter.event.DateEvent;
import io.reactivex.Observable;
import org.apache.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Génère un observable qui emet un événement à intervalle de temps
 * aléatoire, comprise entre 0 et 4 secondes
 */
public class RandomDateObservable {

    private static final Logger LOGGER = Logger.getLogger(RandomDateObservable.class);
    private static final int MIN_SLEEP_TIME_IN_MSSECOND = 100;
    private static final int MAX_SLEEP_TIME_IN_MSSECOND = 4000;

    private boolean active = Boolean.TRUE;
    private Observable<DateEvent> dateStreamObservable;

    public static RandomDateObservable get() {

        RandomDateObservable streamSimulator = new RandomDateObservable();

        Observable<DateEvent> dateStreamObservable = Observable.create(emeteur -> {

            LOGGER.info("Starting event emission - random date");

            while(streamSimulator.isActive()){

                DateEvent event = new DateEvent();

                emeteur.onNext(new DateEvent());
                LOGGER.debug(String.format("Event emited: %s",event));
                sleep();
            }

            emeteur.onComplete();

        });

        streamSimulator.observable(dateStreamObservable);
        return streamSimulator;
    }

    private static void sleep() throws InterruptedException {
        long sleepTime = randomSleepTime();
        TimeUnit.MILLISECONDS.sleep(sleepTime);
    }

    public boolean isActive() {
        return active;
    }

    public void stopEmission () {
        this.active = Boolean.FALSE;
    }

    public Observable<DateEvent> observable () {
        this.active = Boolean.TRUE;
        return this.dateStreamObservable;
    }


    public void observable(Observable<DateEvent> dateStreamObservable) {
        this.dateStreamObservable = dateStreamObservable;
    }

    private static long randomSleepTime() {
        return ThreadLocalRandom.current().nextInt(MIN_SLEEP_TIME_IN_MSSECOND, MAX_SLEEP_TIME_IN_MSSECOND);
    }


}
