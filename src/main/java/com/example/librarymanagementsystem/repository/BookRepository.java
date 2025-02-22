package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(nativeQuery = true,
            value ="select * from model.books b" +
                   " join model.author_book a_b on b.id=a_b.book_id" +
                   " where a_b.author_id=:authorId")
    Optional<List<Book>> findBookByAuthorId(long authorId);
}
