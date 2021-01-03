package com.codegym.demojwt.service;

import com.codegym.demojwt.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CrudRepository<AppUser, Long>, UserDetailsService {
}
