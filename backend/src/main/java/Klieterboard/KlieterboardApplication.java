package Klieterboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Use for starting the Application
 */


@SpringBootApplication
public class KlieterboardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(KlieterboardApplication.class, args);
//        KilterApi ka = ac.getBean(KilterApi.class);
    }

}
