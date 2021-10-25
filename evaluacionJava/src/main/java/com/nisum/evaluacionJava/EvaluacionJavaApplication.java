package com.nisum.evaluacionJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EvaluacionJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluacionJavaApplication.class, args);
    }

}
