package com.ensas.librarymanagementsystem.repositories;

import com.ensas.librarymanagementsystem.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where lower(a.name) like :name")
    Page<Author> findByName(@Param("name") String name, Pageable pageable);


}
