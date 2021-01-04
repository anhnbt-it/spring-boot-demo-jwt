package com.codegym.demojwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JWTApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("AnhNBT.......");
    }
}
