package com.sga.library.service;

import com.sga.library.entity.Author;
import com.sga.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void savePersistsAuthor() {
        Author author = new Author();
        author.setName("Jane Doe");
        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Author saved = authorService.save(author);

        assertThat(saved.getName()).isEqualTo("Jane Doe");
        verify(authorRepository).save(author);
    }

    @Test
    void updateCopiesFieldsIntoExistingAuthor() {
        Author existing = new Author();
        existing.setId(3L);
        existing.setName("Old");
        existing.setEmail("old@example.com");
        when(authorRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Author request = new Author();
        request.setName("New");
        request.setEmail("new@example.com");
        request.setBio("Updated bio");

        Author updated = authorService.update(3L, request);

        assertThat(updated.getEmail()).isEqualTo("new@example.com");
        assertThat(updated.getBio()).isEqualTo("Updated bio");
        verify(authorRepository).save(existing);
    }
}