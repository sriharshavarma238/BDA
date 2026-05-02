package com.sga.library.repository;

import com.sga.library.entity.Author;
import com.sga.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookDetailsReturnsJoinedRows() {
        Author author = new Author();
        author.setName("Test Author");
        author.setEmail("test.author@example.com");
        authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("TEST-ISBN-1");
        book.setPublishedYear(2024);
        book.setPrice(BigDecimal.valueOf(29.99));
        book.setAuthor(author);
        bookRepository.save(book);

        BookAuthorView view = bookRepository.findBookDetails().get(0);
        assertThat(view.getAuthorName()).isEqualTo("Test Author");
        assertThat(view.getTitle()).isEqualTo("Test Book");
    }
}