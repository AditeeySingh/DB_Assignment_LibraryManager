# Library Management System

## Approach

This project is a Spring Boot application designed to manage `Book` and `Author` entities. 
The architecture follows a standard layered approach:
- **Entity Layer**: Defines the JPA entities `Author` and `Book` with a bidirectional One-to-Many / Many-to-One relationship.
- **Repository Layer**: Uses Spring Data JPA (`JpaRepository`) to handle database interactions. A custom JPQL query performs an inner join to fetch books along with their authors efficiently.
- **Service Layer**: Contains business logic and transaction management (`@Transactional`). It includes exception handling for Data Integrity Violations (e.g., duplicate ISBNs).
- **Controller Layer**: Handles HTTP requests (`@Controller`) and routes them to JSP views.
- **View Layer**: Uses JSP and JSTL to render the user interface dynamically.

**Database**: H2 in-memory database is used for simplicity and ease of testing.
**Testing**: JUnit 5 and Mockito are used to mock the repository layer and test the service logic in isolation.

## Entity Relationship Design

- **Author**: Contains an `id`, `name`, `biography`, and a `List<Book>` (One-to-Many).
- **Book**: Contains an `id`, `title`, `isbn` (unique constraint), and an `Author` (Many-to-One).

The `author_id` acts as a foreign key in the `Book` table.

## Implementation Details

### 1. Populate Database
A `DataInitializer` class implementing `CommandLineRunner` inserts 10 sample authors and 10 sample books into the H2 database on startup.

### 2. Create Operation
The `/addBook` endpoint provides a form to add a book. The `LibraryController` handles the form submission, validates the input, and delegates the save operation to `BookService`. Integrity violations (like duplicate ISBNs) are caught and displayed to the user.

### 3. Read Operation
The root endpoint `/` displays all books. `BookRepository` contains a custom inner join query:
```java
@Query("SELECT b FROM Book b JOIN FETCH b.author")
List<Book> findAllBooksWithAuthors();
```

### 4. Update Operation
The `/editBook/{id}` endpoint loads existing details into a form. Submitting updates the entity via JPA's `save()` method.

## Challenges Faced & Solutions

- **JSP Configuration in Spring Boot**: Spring Boot prefers Thymeleaf over JSP. To use JSP, `tomcat-embed-jasper` and JSTL dependencies were manually added, and properties (`spring.mvc.view.prefix` and `spring.mvc.view.suffix`) were configured in `application.properties`.
- **N+1 Query Problem**: Fetching a list of books caused multiple queries to fetch their respective authors. **Solution**: Created a custom JPQL query using `JOIN FETCH` to eagerly load the author details in a single query.
- **Exception Handling**: Handling unique constraint violations for the ISBN seamlessly. **Solution**: Caught `DataIntegrityViolationException` in the service layer and propagated a user-friendly message to the controller to display in the JSP.

## Github URL

This project has been pushed to the following GitHub repository:
[https://github.com/AditeeySingh/DB_Assignment_LibraryManager](https://github.com/AditeeySingh/DB_Assignment_LibraryManager)

```bash
git remote add origin https://github.com/AditeeySingh/DB_Assignment_LibraryManager.git
git branch -M main
git push -u origin main
```

## Screenshots and Demo

<img width="1687" height="518" alt="Screenshot 2026-05-03 at 12 11 59 AM" src="https://github.com/user-attachments/assets/ad40d10f-a7d8-4cc3-924d-6eea46a0c7db" />
Add New Author

<img width="1695" height="530" alt="Screenshot 2026-05-03 at 12 12 29 AM" src="https://github.com/user-attachments/assets/19f2d216-bbcf-4f60-bebc-6d20b8575759" />
Add New Book

<img width="1686" height="735" alt="Screenshot 2026-05-03 at 12 12 40 AM" src="https://github.com/user-attachments/assets/a0df3550-1713-4f79-84b0-8d0f66711256" />
All the books in the library 

<img width="1690" height="627" alt="Screenshot 2026-05-03 at 12 12 49 AM" src="https://github.com/user-attachments/assets/c1167392-31c5-49ad-bb83-17109707e9d7" />
Edit book info
