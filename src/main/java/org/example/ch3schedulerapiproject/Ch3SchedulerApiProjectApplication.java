package org.example.ch3schedulerapiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3SchedulerApiProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3SchedulerApiProjectApplication.class, args);
    }

}
