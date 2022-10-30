## 📚 Library Management System Java/Spring Boot/Thymeleaf/PostgreSQL

A Library Management System made using the concepts of Object Oriented Analysis and Design. 


### Actors:
The actors include the following: 
* Student
* Borrower
* Administrator


### Use Cases:
After determining the actors, the second step in use case analysis is to determine the tasks that each actor will need to do with the system. Each task is called a use case because it represents one particular way the system will be used.

**In other words, only those use cases are listed that actors will need to do when they are using the system to solve the customer’s problem.** 


#### Student:
* ❏ Search for books by name.
* ❏ ... by author.
* ❏ ... by category.
* ❏ See details of a book.
* ❏ ... an author.
* ❏ ... a category.
* ❏ Borrow a new Book. **if a book is available the return date should be between the current date and current date plus three months.**
* ❏ Check the list of books currently borrowed.
* ❏ Return borrowed books.

#### Administrator:
* ❏ Search for books by book name.
* ❏ ... by author.
* ❏ ... by category.
* See details of a book.
* ❏ ... an author.
* ❏ ... a category.
* ❏ Update the available quantity of a book.
* ❏ Add/delete/modify a Book.
* ❏ Add/delete/modify a Category.
* ❏ Add/delete/modify an Author.


### Tech stack:
   * Spring Boot
   * Spring Data Jpa
   * H2 Database
   * Thymeleaf
   * Bootstrap4
