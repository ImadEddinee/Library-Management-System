## π Library Management System Java/Spring Boot/Thymeleaf/PostgreSQL

A Library Management System made using the concepts of Object Oriented Analysis and Design. 


### Actors:
The actors include the following: 
* Student
* Borrower
* Administrator


### Use Cases:
After determining the actors, the second step in use case analysis is to determine the tasks that each actor will need to do with the system. Each task is called a use case because it represents one particular way the system will be used.

**In other words, only those use cases are listed that actors will need to do when they are using the system to solve the customerβs problem.** 


#### Student:
* β Search for books by name.
* β ... by author.
* β ... by category.
* β See details of a book.
* β ... an author.
* β ... a category.
* β Borrow a new Book. **if a book is available the return date should be between the current date and current date plus three months.**
* β Check the list of books currently borrowed.
* β Return borrowed books.

#### Administrator:
* β Search for books by book name.
* β ... by author.
* β ... by category.
* See details of a book.
* β ... an author.
* β ... a category.
* β Update the available quantity of a book.
* β Add/delete/modify a Book.
* β Add/delete/modify a Category.
* β Add/delete/modify an Author.


### Tech stack:
   * Spring Boot
   * Spring Data Jpa
   * H2 Database
   * Thymeleaf
   * Bootstrap4
