package com.codegym.demojwt;

import com.codegym.demojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JWTApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello.......");
//        AppUser user = new AppUser();
//        user.setEmail("anhnbt.it@gmail.com");
//        user.setName("Tuan Anh");
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        userRepository.save(user);
//        System.out.println(user.toString());
    }
}
