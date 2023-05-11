package webuild.esprit.tn.tunisiacampwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableScheduling
public class TunisiaCampWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(TunisiaCampWebApplication.class, args);
    }

}
