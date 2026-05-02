package com.sga.library.controller;

import com.sga.library.entity.Author;
import com.sga.library.exception.ResourceNotFoundException;
import com.sga.library.service.AuthorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String list(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/authors/new")
    public String createForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }

    @PostMapping("/authors")
    public String create(@ModelAttribute("author") Author author, Model model, RedirectAttributes redirectAttributes) {
        try {
            authorService.save(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author created successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Author email must be unique.");
            return "authors/form";
        }
    }

    @GetMapping("/authors/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("author", authorService.findById(id));
            return "authors/form";
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/authors";
        }
    }

    @PostMapping("/authors/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("author") Author author, Model model, RedirectAttributes redirectAttributes) {
        try {
            authorService.update(id, author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            author.setId(id);
            model.addAttribute("author", author);
            model.addAttribute("errorMessage", "Author email must be unique.");
            return "authors/form";
        }
    }
}