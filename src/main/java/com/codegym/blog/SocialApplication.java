package com.codegym.blog;

import com.codegym.blog.model.User;
import com.codegym.blog.model.Role;
import com.codegym.blog.service.RoleService;
import com.codegym.blog.service.UserService;
import com.codegym.blog.storage.StorageProperties;
import com.codegym.blog.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SocialApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SocialApplication.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.save(new Role("ROLE_ADMIN"));
            log.info("Create ROLE_ADMIN");
        }
        log.info("-------------------------------");
        if (roleService.findByName("ROLE_MEMBER") == null) {
            roleService.save(new Role("ROLE_MEMBER"));
            log.info("Create ROLE_MEMBER");
        }
        log.info("-------------------------------");
        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_ADMIN"));
            roles.add(roleService.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userService.save(admin);
            log.info("Create admin account 'admin' password: 'admin'");
        }
        log.info("-------------------------------");
        if (userService.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("user"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userService.save(user);
            log.info("Create user account 'user' password: 'user'");
        }
        log.info("-------------------------------");
    }
}
