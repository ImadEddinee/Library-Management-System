package com.ensas.librarymanagementsystem.service.impl;


import com.ensas.librarymanagementsystem.entities.security.Authority;
import com.ensas.librarymanagementsystem.repositories.AuthorityRepository;
import com.ensas.librarymanagementsystem.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

}
