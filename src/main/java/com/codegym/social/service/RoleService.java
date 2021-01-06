package com.codegym.social.service;

import com.codegym.social.model.Role;

public interface RoleService {
    Role findByName(String name);

    Role save(Role role);
}
