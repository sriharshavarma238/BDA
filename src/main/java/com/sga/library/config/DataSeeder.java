package com.sga.library.config;

import com.sga.library.dto.BookForm;
import com.sga.library.entity.Author;
import com.sga.library.repository.AuthorRepository;
import com.sga.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(AuthorRepository authorRepository, BookService bookService) {
        return args -> {
            if (authorRepository.count() > 0) {
                return;
            }

            Author[] authors = new Author[10];
            for (int index = 0; index < 10; index++) {
                Author author = new Author();
                author.setName("Author " + (index + 1));
                author.setEmail("author" + (index + 1) + "@example.com");
                author.setBio("Bio for Author " + (index + 1));
                authors[index] = authorRepository.save(author);
            }

            for (int index = 0; index < 10; index++) {
                BookForm form = new BookForm();
                form.setTitle("Book " + (index + 1));
                form.setIsbn("ISBN-1000" + (index + 1));
                form.setPublishedYear(2001 + index);
                form.setPrice(BigDecimal.valueOf(19.99).add(BigDecimal.valueOf(index)));
                form.setAuthorId(authors[index].getId());
                bookService.save(form);
            }
        };
    }
}