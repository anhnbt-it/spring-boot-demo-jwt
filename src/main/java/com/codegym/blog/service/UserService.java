package com.codegym.blog.service;

import com.codegym.blog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findByUsername(String name);

    Iterable<User> findAll();

    Optional<User> findById(Long id);
}
