package com.ensas.librarymanagementsystem.entities;

import com.ensas.librarymanagementsystem.entities.security.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@Table(name = "BORROW")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isReturned;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
}
