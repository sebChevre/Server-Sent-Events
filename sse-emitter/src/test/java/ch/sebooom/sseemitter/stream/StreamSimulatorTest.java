package ch.sebooom.sseemitter.stream;

import ch.sebooom.sseemitter.emiter.RandomDateObservable;
import ch.sebooom.sseemitter.event.DateEvent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
public class StreamSimulatorTest {

    @Test
    public void assertFonctionnementStandard () {

        RandomDateObservable simu = RandomDateObservable.get();

        List<DateEvent> evenements = new ArrayList<>();

        simu.observable().subscribe(
                next->{
                    assertTrue(next instanceof DateEvent);
                    System.out.println(next);
                    evenements.add(next);

                    if(evenements.size() == 3){
                        simu.stopEmission();
                    }
                },
                (erreur)->{

                },
                ()->{
                    assertTrue(evenements.size()==3);
                }
        );

    }
}