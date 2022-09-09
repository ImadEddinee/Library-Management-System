package com.ensas.librarymanagementsystem.repositories;

import com.ensas.librarymanagementsystem.entities.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {

    @Query("select b from Borrow b where b.book.id = :bookId and b.user.id = :userId")
    Optional<Borrow> findIfAlreadyBorrowed(@Param("bookId") Long bookId, @Param("userId") UUID userId);

    @Query("select b from Borrow b where b.user.id = :userId and lower(b.book.name) like :name and b.isReturned = false ")
    Page<Borrow> findBorrowedBooks(@Param("userId") UUID userId,@Param("name") String name,Pageable pageable);
}
