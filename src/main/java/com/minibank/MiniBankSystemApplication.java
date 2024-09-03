package com.minibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiniBankSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniBankSystemApplication.class, args);
    }
}

