package ch.sebooom.sseemitter;

import ch.sebooom.sseemitter.emiter.RandomDateEventEmiter;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@SpringBootApplication(scanBasePackages = { "ch.sebooom" })
@EnableScheduling
public class Application {


    private static final Logger LOGGER = Logger.getLogger(Application.class);


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<SSEEmeteur> emeteurs () {
        return new ArrayList<>();
    }

    @Bean
    public RandomDateEventEmiter randomDateEventEmiter () {

        return new RandomDateEventEmiter();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor taskExecutor) {

        LOGGER.info("CommandLineRunner starting...");

        return new CommandLineRunner() {

            public void run(String... args) throws Exception {
                taskExecutor.execute(randomDateEventEmiter());
            }
        };
    }



}
