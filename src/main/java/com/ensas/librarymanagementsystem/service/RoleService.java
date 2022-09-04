package com.ensas.librarymanagementsystem.service;


import com.ensas.librarymanagementsystem.entities.security.Authority;
import com.ensas.librarymanagementsystem.entities.security.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role saveRole(Role role);

    void addAuthoritiesToRole(UUID uuid, List<Authority> authorities);
}
