package com.codegym.social.service;

import com.codegym.social.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findByUsername(String name);

    Iterable<User> findAll();

    Optional<User> findById(Long id);
}
