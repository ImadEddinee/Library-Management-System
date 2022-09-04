package com.ensas.librarymanagementsystem.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 6,max = 20)
    @Column(length = 70, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String description;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

}
