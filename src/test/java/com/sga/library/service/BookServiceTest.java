package com.sga.library.service;

import com.sga.library.dto.BookForm;
import com.sga.library.entity.Author;
import com.sga.library.entity.Book;
import com.sga.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void saveMapsFormToEntity() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Author 1");
        when(authorService.findById(1L)).thenReturn(author);
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookForm form = new BookForm();
        form.setTitle("Domain Driven Design");
        form.setIsbn("DDD-001");
        form.setPublishedYear(2003);
        form.setPrice(BigDecimal.valueOf(44.50));
        form.setAuthorId(1L);

        Book saved = bookService.save(form);

        assertThat(saved.getTitle()).isEqualTo("Domain Driven Design");
        assertThat(saved.getAuthor().getId()).isEqualTo(1L);
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateUsesExistingBook() {
        Author author = new Author();
        author.setId(2L);
        author.setName("Author 2");
        when(authorService.findById(2L)).thenReturn(author);

        Book existing = new Book();
        existing.setId(9L);
        existing.setTitle("Old Title");
        existing.setIsbn("OLD-1");
        existing.setPublishedYear(1999);
        existing.setPrice(BigDecimal.ONE);
        existing.setAuthor(author);

        when(bookRepository.findById(9L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookForm form = new BookForm();
        form.setTitle("New Title");
        form.setIsbn("NEW-1");
        form.setPublishedYear(2025);
        form.setPrice(BigDecimal.valueOf(55.75));
        form.setAuthorId(2L);

        Book updated = bookService.update(9L, form);

        assertThat(updated.getTitle()).isEqualTo("New Title");
        assertThat(updated.getPublishedYear()).isEqualTo(2025);
        verify(bookRepository).save(existing);
    }
}