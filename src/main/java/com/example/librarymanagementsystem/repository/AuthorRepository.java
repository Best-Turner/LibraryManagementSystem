package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
