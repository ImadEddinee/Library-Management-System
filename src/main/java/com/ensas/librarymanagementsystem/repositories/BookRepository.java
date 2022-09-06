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

    @Query(nativeQuery = true,value = "select b.id,b.isbn,b.name,b.description from book b inner join book_category c on b.id = c.book_id where c.category_id = :category and lower(b.name) like :name")
    Page<Book> findBooksByCategoryId(@Param("category") Long category,@Param("name") String name, Pageable pageable);

    @Query(nativeQuery = true,value = "select b.id,b.isbn,b.name,b.description from book b inner join book_author au on b.id = au.book_id where au.author_id = :author and lower(b.name) like :name")
    Page<Book> findBooksByAuthorId(@Param("author") Long author,@Param("name") String name, Pageable pageable);
}
