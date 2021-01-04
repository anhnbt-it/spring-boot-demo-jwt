package com.codegym.demojwt.service;

import com.codegym.demojwt.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser save(AppUser user);
}
