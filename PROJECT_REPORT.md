# Project Report

## Entity Relationship Design

The application uses two entities: `Author` and `Book`.

- One `Author` can have many `Book` records.
- Each `Book` belongs to exactly one `Author`.
- The relationship is implemented with `@OneToMany` on `Author` and `@ManyToOne` on `Book`.

## Implementation Details

### Create

- JSP forms were created for authors and books.
- Controllers accept form submissions and persist the new records through the service layer.
- Integrity violations such as duplicate email or ISBN values are handled and shown in the JSP page.

### Read

- Authors and books are displayed on separate JSP list pages.
- The book list uses a custom repository query with an inner join to show the book title and the related author name.

### Update

- Edit links open prefilled JSP forms.
- Controllers load the existing entity, apply user changes, and save the updated record.

## Sample Data

- A startup seeder creates 10 authors and 10 books.
- This ensures the database is populated immediately after launch.

## Testing

- Repository tests verify the custom join query.
- Service tests use Mockito to validate mapping and update logic.

## Challenges and Solutions

- JSP support in Spring Boot required WAR packaging and Jasper/JSTL dependencies.
- Referential data entry was simplified using a form DTO for books so the author selection could be bound cleanly.

## Github URL

- https://github.com/sriharshavarma238/BDA.git