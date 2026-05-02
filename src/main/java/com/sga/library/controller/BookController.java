package com.sga.library.controller;

import com.sga.library.dto.BookForm;
import com.sga.library.exception.ResourceNotFoundException;
import com.sga.library.service.AuthorService;
import com.sga.library.service.BookService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String list(Model model) {
        model.addAttribute("bookDetails", bookService.findJoinedDetails());
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/books/new")
    public String createForm(Model model) {
        model.addAttribute("book", new BookForm());
        model.addAttribute("authors", authorService.findAll());
        return "books/form";
    }

    @PostMapping("/books")
    public String create(@ModelAttribute("book") BookForm book, Model model, RedirectAttributes redirectAttributes) {
        try {
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book created successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", "Book ISBN must be unique and an author must be selected.");
            return "books/form";
        }
    }

    @GetMapping("/books/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            var book = bookService.findById(id);
            BookForm form = new BookForm();
            form.setId(book.getId());
            form.setTitle(book.getTitle());
            form.setIsbn(book.getIsbn());
            form.setPublishedYear(book.getPublishedYear());
            form.setPrice(book.getPrice());
            form.setAuthorId(book.getAuthor().getId());
            model.addAttribute("book", form);
            model.addAttribute("authors", authorService.findAll());
            return "books/form";
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/books";
        }
    }

    @PostMapping("/books/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("book") BookForm book, Model model, RedirectAttributes redirectAttributes) {
        try {
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", "Book ISBN must be unique and an author must be selected.");
            return "books/form";
        }
    }
}