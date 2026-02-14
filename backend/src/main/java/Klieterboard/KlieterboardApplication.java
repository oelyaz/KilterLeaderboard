package Klieterboard;

import Klieterboard.projectRepository.Logbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

/**
 * Use for starting the Application
 */

@EnableScheduling
@SpringBootApplication
public class KlieterboardApplication {

    public static void main(String[] args) {
        System.out.println("Starting Klieterboard - User-Agent App");
        ConfigurableApplicationContext ac = SpringApplication.run(KlieterboardApplication.class, args);
        Logbook.setSeasonStart(LocalDateTime.of(2026, 1, 1, 0, 0, 0));
//        KilterApi ka = ac.getBean(KilterApi.class);
    }

}
