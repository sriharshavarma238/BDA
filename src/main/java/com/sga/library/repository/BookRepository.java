package com.sga.library.repository;

import com.sga.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
            select b.id as id,
                   b.title as title,
                   b.isbn as isbn,
                   b.publishedYear as publishedYear,
                   b.price as price,
                   a.name as authorName
            from Book b
            join b.author a
            order by b.title
            """)
    List<BookAuthorView> findBookDetails();
}