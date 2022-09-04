package com.ensas.librarymanagementsystem.service.impl;


import com.ensas.librarymanagementsystem.entities.security.Authority;
import com.ensas.librarymanagementsystem.entities.security.Role;
import com.ensas.librarymanagementsystem.repositories.RoleRepository;
import com.ensas.librarymanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addAuthoritiesToRole(UUID uuid, List<Authority> authorities) {
        if(authorities != null && authorities.size()>0)  {
            Role role = roleRepository.findById(uuid).get();

            Set<String> authoritiesNames = role.getAuthorities().stream().map(Authority::getPermission).collect(Collectors.toSet());

            List<Authority> authoritiesToAdd = authorities.stream().filter(e->!authoritiesNames.contains(e.getPermission())).collect(Collectors.toList());

            role.getAuthorities().addAll(authoritiesToAdd);
            roleRepository.save(role);
        }
    }
}
