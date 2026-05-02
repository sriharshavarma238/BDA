package com.sga.library.service;

import com.sga.library.dto.BookForm;
import com.sga.library.entity.Author;
import com.sga.library.entity.Book;
import com.sga.library.exception.ResourceNotFoundException;
import com.sga.library.repository.BookAuthorView;
import com.sga.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<BookAuthorView> findJoinedDetails() {
        return bookRepository.findBookDetails();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public Book save(BookForm form) {
        Book book = new Book();
        applyForm(book, form);
        return bookRepository.save(book);
    }

    public Book update(Long id, BookForm form) {
        Book existing = findById(id);
        applyForm(existing, form);
        return bookRepository.save(existing);
    }

    private void applyForm(Book book, BookForm form) {
        book.setTitle(form.getTitle());
        book.setIsbn(form.getIsbn());
        book.setPublishedYear(form.getPublishedYear());
        book.setPrice(form.getPrice());
        Author author = authorService.findById(form.getAuthorId());
        book.setAuthor(author);
    }
}