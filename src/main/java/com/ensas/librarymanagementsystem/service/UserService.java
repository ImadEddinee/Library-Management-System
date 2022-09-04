package com.ensas.librarymanagementsystem.service;


import com.ensas.librarymanagementsystem.entities.security.Role;
import com.ensas.librarymanagementsystem.entities.security.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    void UpdateUser(User user);

    void addRolesToUser(UUID uuid, List<Role> roles);

    User loadUserByUsername(String username);
}
