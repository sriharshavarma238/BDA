package com.sga.library.service;

import com.sga.library.entity.Author;
import com.sga.library.exception.ResourceNotFoundException;
import com.sga.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author request) {
        Author existing = findById(id);
        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setBio(request.getBio());
        return authorRepository.save(existing);
    }
}