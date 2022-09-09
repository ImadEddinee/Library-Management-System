package com.ensas.librarymanagementsystem.bootstrap;

import com.ensas.librarymanagementsystem.entities.Author;
import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.entities.Borrow;
import com.ensas.librarymanagementsystem.entities.Category;
import com.ensas.librarymanagementsystem.entities.security.Authority;
import com.ensas.librarymanagementsystem.entities.security.Role;
import com.ensas.librarymanagementsystem.entities.security.User;
import com.ensas.librarymanagementsystem.repositories.*;
import com.ensas.librarymanagementsystem.service.UserService;
import com.ensas.librarymanagementsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserServiceImpl userService;
    private final BorrowRepository borrowRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Authority addBook = new Authority();
        addBook.setPermission("create_book");
        authorityRepository.save(addBook);

        Authority deleteBook = new Authority();
        deleteBook.setPermission("delete_book");
        authorityRepository.save(deleteBook);

        Authority updateBook = new Authority();
        updateBook.setPermission("update_book");
        authorityRepository.save(updateBook);

        Authority borrowBook = new Authority();
        borrowBook.setPermission("borrow_book");
        authorityRepository.save(borrowBook);

        Authority addCategory = new Authority();
        addCategory.setPermission("create_category");
        authorityRepository.save(addCategory);

        Authority deleteCategory = new Authority();
        deleteCategory.setPermission("delete_category");
        authorityRepository.save(deleteBook);

        Authority updateCategory = new Authority();
        updateCategory.setPermission("update_category");
        authorityRepository.save(updateBook);

        Authority addAuthor = new Authority();
        addAuthor.setPermission("create_author");
        authorityRepository.save(addAuthor);

        Authority deleteAuthor = new Authority();
        deleteAuthor.setPermission("delete_author");
        authorityRepository.save(deleteAuthor);

        Authority updateAuthor = new Authority();
        updateAuthor.setPermission("update_author");
        authorityRepository.save(updateAuthor);

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Set<Authority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(addBook);
        adminAuthorities.add(deleteBook);
        adminAuthorities.add(updateBook);
        adminAuthorities.add(addCategory);
        adminAuthorities.add(deleteCategory);
        adminAuthorities.add(updateCategory);
        adminAuthorities.add(addAuthor);
        adminAuthorities.add(deleteAuthor);
        adminAuthorities.add(updateAuthor);
        adminRole.setAuthorities(adminAuthorities);

        roleRepository.save(adminRole);

        Role studentRole = new Role();
        studentRole.setName("STUDENT");
        roleRepository.save(studentRole);

        Set<Authority> studentAuthorities = new HashSet<>();
        studentAuthorities.add(borrowBook);
        studentRole.setAuthorities(studentAuthorities);

        roleRepository.save(studentRole);

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@gmail.com");
        admin.setPhone("0746598475");
        admin.setAddress("address admin");
        userRepository.save(admin);
        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        userService.addRolesToUser(admin.getId(),roles);

        User student = new User();
        student.setFirstName("student");
        student.setLastName("student");
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("student"));
        student.setEmail("student@gmail.com");
        student.setPhone("0746598475");
        student.setAddress("address student");
        userRepository.save(student);
        List<Role> roles1 = new ArrayList<>();
        roles1.add(studentRole);
        userService.addRolesToUser(student.getId(),roles1);


        Category category = new Category();
        category.setName("category name");
        categoryRepository.save(category);


        Author author = new Author();
        author.setName("author name");
        author.setDescription("author description");
        authorRepository.save(author);


        Book book = new Book();
        book.setIsbn("Book ISBNsegser");
        book.setName("Book name");
        book.setDescription("book description");
        book.setQuantity(1);
        bookRepository.save(book);

        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setReturned(false);
        borrow.setUser(student);
        borrow.setBorrowedAt(LocalDate.now());
        borrowRepository.save(borrow);

        book = new Book();
        book.setIsbn("Book ISerBN");
        book.setName("Book name2");
        book.setDescription("book description");
        book.setQuantity(0);
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book sgseISBN");
        book.setName("Book name3");
        book.setQuantity(1);
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Bosergok ISBN");
        book.setName("Book name4");
        book.setQuantity(1);
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBsgeN");
        book.setQuantity(1);
        book.setName("Book name52");
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBntyN");
        book.setQuantity(1);
        book.setName("Book name54");
        book.setDescription("book description");
        bookRepository.save(book);





        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBntN");
        book.setQuantity(1);
        book.setName("Book name65");
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBNgd");
        book.setQuantity(1);
        book.setName("Book name25");
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBjuN");
        book.setName("Book name75");
        book.setQuantity(1);
        book.setDescription("book description");
        bookRepository.save(book);




        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBjytN");
        book.setName("Book name77");
        book.setDescription("book description");
        book.setQuantity(1);
        bookRepository.save(book);





        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBbtrN");
        book.setQuantity(1);
        book.setName("Book nameg7");
        book.setDescription("book description");
        bookRepository.save(book);





        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISBgreN");
        book.setQuantity(1);
        book.setName("Book namek7");
        book.setDescription("book description");
        bookRepository.save(book);





        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book ISewfBN");
        book.setQuantity(1);
        book.setName("Book namekj7");
        book.setDescription("book description");
        bookRepository.save(book);





        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);

        book = new Book();
        book.setIsbn("Book IwefSBN");
        book.setQuantity(1);
        book.setName("Book naf7me");
        book.setDescription("book description");
        bookRepository.save(book);






        book.addCategory(category);
        book.addAuthor(author);
        bookRepository.save(book);
    }
}
