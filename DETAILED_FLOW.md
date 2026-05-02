# Detailed Project Flow

## 1. Project Goal

This project is a Spring Boot web application that manages information for two related entities: `Author` and `Book`.

The purpose of the application is to demonstrate the complete CRUD flow for a real-world style domain using:

- Spring Boot for application startup and dependency management
- Spring MVC for request handling
- Spring Data JPA for database access
- JSP for the view layer
- H2 for development-time data storage
- JUnit 5 and Mockito for testing

The application focuses on three required operations:

- Create new authors and books
- Read and display authors and books
- Update existing authors and books

It also includes:

- A custom inner join query in the repository layer
- Sample data initialization with 10 rows in each table
- Integrity violation handling for duplicate values and invalid references

## 2. Application Architecture

The application follows a layered design so that each part of the code has a single responsibility.

### 2.1 Controller Layer

The controller layer handles HTTP requests coming from the JSP pages.

It is responsible for:

- Receiving form submissions
- Calling service methods
- Preparing data for JSP views
- Redirecting after successful create or update actions
- Returning error messages when validation or database constraints fail

### 2.2 Service Layer

The service layer contains the business logic.

It is responsible for:

- Fetching and saving entities through repositories
- Applying updates to existing entities
- Resolving relationships between books and authors
- Throwing a clear exception when a record is not found

### 2.3 Repository Layer

The repository layer is the database access layer.

It is responsible for:

- Storing and retrieving `Author` and `Book` records
- Returning all rows for list pages
- Running the custom join query for the book-author read view

### 2.4 Entity Layer

The entity layer defines the database structure using JPA annotations.

It includes:

- `Author`
- `Book`

The relationship is:

- One author can have many books
- One book belongs to one author

### 2.5 View Layer

The view layer is implemented using JSP pages.

It provides:

- A home page
- Author list and form pages
- Book list and form pages
- A simple error page

The UI uses CSS styling to make the pages visually cleaner and easier to read.

## 3. Entity Relationship Design

The chosen entities are `Author` and `Book`.

### Author Entity

The author stores:

- `id`
- `name`
- `email`
- `bio`

The `email` field is marked unique so two authors cannot share the same email address.

### Book Entity

The book stores:

- `id`
- `title`
- `isbn`
- `publishedYear`
- `price`
- `author`

The `isbn` field is also unique.

### Relationship

The relationship is implemented using:

- `@OneToMany(mappedBy = "author")` in `Author`
- `@ManyToOne(fetch = FetchType.LAZY)` in `Book`
- `@JoinColumn(name = "author_id")` in `Book`

This structure means the `books` table contains a foreign key pointing to the `authors` table.

## 4. Request Flow Overview

The application flow is the same for most user actions:

1. The user opens a JSP page in the browser.
2. The page submits a form or requests a list page.
3. Spring MVC routes the request to the correct controller method.
4. The controller calls the service layer.
5. The service layer calls the repository layer.
6. JPA reads from or writes to the database.
7. The result is returned to the controller.
8. The controller sends data back to a JSP page or redirects to a list page.

This separation keeps the code easy to maintain and easy to explain in a project submission.

## 5. Create Flow

The create operation is implemented for both authors and books.

### 5.1 Author Create Flow

1. The user opens the author form page.
2. The JSP displays input fields for name, email, and bio.
3. The user submits the form.
4. The controller receives the posted `Author` object.
5. The service layer saves the author through `AuthorRepository`.
6. If the save is successful, the controller redirects to the author list page.
7. If the email already exists, Spring throws a data integrity exception and the controller returns the form with an error message.

### 5.2 Book Create Flow

1. The user opens the book form page.
2. The controller loads the list of authors so the user can select one.
3. The JSP displays fields for title, ISBN, published year, price, and author.
4. The user submits the form.
5. The controller receives a `BookForm` object.
6. The service layer converts the form data into a `Book` entity.
7. The service fetches the selected author by ID.
8. The book is saved through `BookRepository`.
9. If the ISBN already exists or the foreign key is invalid, the controller displays an error message and reloads the form.

### 5.3 Why a DTO is Used for Books

The book form uses a separate DTO named `BookForm`.

This is done because:

- The form needs an `authorId` field rather than a full author object
- It keeps the JSP simple
- It avoids binding issues when posting nested entity objects directly from the browser

## 6. Read Flow

The read operation shows the current data saved in the database.

### 6.1 Author Read Flow

1. The user opens the authors list page.
2. The controller calls `authorService.findAll()`.
3. The service fetches all author rows from the database.
4. The controller adds the result to the model.
5. The JSP iterates through the list and displays each author in a table.

### 6.2 Book Read Flow

1. The user opens the books list page.
2. The controller calls two service methods:
   - `bookService.findAll()`
   - `bookService.findJoinedDetails()`
3. The regular list shows all book entities.
4. The joined detail view is populated using a custom repository query.
5. The JSP displays book title, ISBN, year, price, and author name.

### 6.3 Custom Inner Join Query

The repository contains a JPQL query that joins `Book` and `Author`.

The query returns a projection that includes:

- Book ID
- Title
- ISBN
- Published year
- Price
- Author name

This query demonstrates how to read combined data from related entities in one database call.

## 7. Update Flow

The update operation works for both authors and books.

### 7.1 Author Update Flow

1. The user clicks the edit link for an author.
2. The controller loads the existing author by ID.
3. The JSP form is prefilled with the current author values.
4. The user edits the fields and submits the form.
5. The controller sends the updated author data to the service layer.
6. The service loads the existing entity, copies the changed fields, and saves it.
7. The controller redirects to the authors list page.

### 7.2 Book Update Flow

1. The user clicks the edit link for a book.
2. The controller loads the book and maps it to `BookForm`.
3. The form is shown with the current values already selected.
4. The user changes the data and submits the form.
5. The service looks up the selected author ID.
6. The existing book record is updated and saved.
7. The controller redirects back to the books list page.

## 8. Database Population

The application includes a startup seeder class.

### What the Seeder Does

When the application starts:

1. The seeder checks whether the author table already has records.
2. If the database is empty, it creates 10 authors.
3. It then creates 10 books.
4. Each book is linked to one of the created authors.

### Why Seeding Is Useful

Seeding makes it easy to:

- Demonstrate the application immediately after startup
- Show data in the list pages without manual entry
- Satisfy the assignment requirement for populating each table with sample data

## 9. Exception Handling

Exception handling is included to make the application safer and easier to use.

### Integrity Violations

The create and update flows catch data integrity exceptions.

These can happen when:

- Two authors use the same email
- Two books use the same ISBN
- A book is saved with an invalid author reference

When that happens, the controller returns the same JSP form and shows a user-friendly error message.

### Not Found Errors

If a user tries to edit a record that does not exist, the service throws a custom `ResourceNotFoundException`.

The global exception handler catches it and shows the error page.

## 10. Testing Strategy

The project includes unit tests for both repository and service behavior.

### Repository Test

The repository test checks that the custom join query returns the expected author name and book title.

This confirms that:

- The relationship is mapped correctly
- The join query works as intended
- The projection interface receives the right data

### Service Tests

Mockito-based service tests verify:

- Form-to-entity mapping for book creation
- Updating existing author and book records
- Interaction with repositories

These tests confirm that the business logic is correct without needing a full web request.

## 11. View Layer Details

The JSP pages are intentionally simple and functional.

### Pages Included

- `home.jsp`
- `authors/list.jsp`
- `authors/form.jsp`
- `books/list.jsp`
- `books/form.jsp`
- `error.jsp`

### Styling

The CSS file provides:

- A clean card layout
- Table styling for records
- Form spacing and input formatting
- Simple success and error alerts
- Basic responsiveness for smaller screens

## 12. Why the Project Is Structured This Way

This structure was chosen because it matches standard Spring Boot application design.

It makes the project easier to explain in a report because each layer has a clear role:

- Controllers manage web requests
- Services manage business logic
- Repositories manage database access
- JSP files manage presentation
- Entities define the data model

This clear separation is also important for testing and maintenance.

## 13. How to Run the Project

1. Open the project in a Java 17 environment.
2. Run the Spring Boot application.
3. Open the home page in the browser.
4. Navigate to Authors or Books.
5. Create a new record or edit an existing one.
6. Observe the table updates after each successful action.

## 14. Summary

This project demonstrates a complete Spring Boot web application flow from browser request to database update and back to the UI.

It shows:

- Entity modeling with JPA
- Service-based business logic
- Repository access and custom join queries
- JSP-based views
- Exception handling
- Sample data population
- Unit testing

The result is a full CRUD application that can be used as a submission project and as a reference for understanding how a Spring Boot MVC application is organized.