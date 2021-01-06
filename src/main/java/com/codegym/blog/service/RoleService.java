package com.codegym.blog.service;

import com.codegym.blog.model.Role;

public interface RoleService {
    Role findByName(String name);

    Role save(Role role);
}
