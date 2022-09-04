package com.ensas.librarymanagementsystem.repositories;

import com.ensas.librarymanagementsystem.entities.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
}
