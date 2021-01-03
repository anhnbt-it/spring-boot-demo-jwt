package com.codegym.demojwt.repository;

import com.codegym.demojwt.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    List<AppUser> findByNameContains(String name);

    Optional<AppUser> findByUsername(String username);
}
