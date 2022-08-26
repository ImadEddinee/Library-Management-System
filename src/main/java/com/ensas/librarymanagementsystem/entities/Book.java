package com.ensas.librarymanagementsystem.entities;

import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "BOOK")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 55,nullable = false,unique = true)
    private String isbn;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}
