# Library Manager

Spring Boot JSP application for managing authors and books with create, read, and update operations.

## Features

- Author and book entities with a one-to-many relationship
- JSP pages for list, create, and update flows
- Custom JPA inner join query for joined book/author results
- Sample data seeding with 10 authors and 10 books
- Repository and service unit tests with JUnit 5 and Mockito

## Run

```bash
mvn spring-boot:run
```

Open `http://localhost:8080` after startup.

## Notes

- H2 is used for development and testing.
- JSP pages are stored under `src/main/webapp/WEB-INF/jsp`.
- The project is packaged as a WAR to support JSP rendering cleanly.