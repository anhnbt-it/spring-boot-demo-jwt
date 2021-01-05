package com.codegym.demojwt;

import com.codegym.demojwt.model.AppUser;
import com.codegym.demojwt.model.Role;
import com.codegym.demojwt.service.RoleService;
import com.codegym.demojwt.service.UserService;
import com.codegym.demojwt.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class JWTApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Roles
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.save(new Role("ROLE_ADMIN"));
        }

        if (roleService.findByName("ROLE_MEMBER") == null) {
            roleService.save(new Role("ROLE_MEMBER"));
        }

        // Admin account
        if (userService.findByUsername("admin") == null) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_ADMIN"));
            roles.add(roleService.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userService.save(admin);
        }

        // User account
        if (userService.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("user"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userService.save(user);
        }
    }
}
