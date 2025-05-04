package Klieterboard;

import Klieterboard.projectRepository.Logbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

/**
 * Use for starting the Application
 */


@SpringBootApplication
public class KlieterboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(KlieterboardApplication.class, args);
        Logbook.setSeasonStart(LocalDateTime.of(2025, 1, 1, 0, 0, 0));
//        KilterApi ka = ac.getBean(KilterApi.class);
    }

}
