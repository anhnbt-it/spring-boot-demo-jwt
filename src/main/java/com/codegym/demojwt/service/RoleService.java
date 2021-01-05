package com.codegym.demojwt.service;

import com.codegym.demojwt.model.Role;

public interface RoleService {
    Role findByName(String name);

    Role save(Role role);
}
