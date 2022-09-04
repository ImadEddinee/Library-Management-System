package com.ensas.librarymanagementsystem.repositories;

import com.ensas.librarymanagementsystem.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select b from Book b where lower(b.name) like :name")
    Page<Book> findBooksByName(@Param("name") String name, Pageable pageable);

}
