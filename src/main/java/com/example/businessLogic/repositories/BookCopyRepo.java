package com.example.businessLogic.repositories;

import com.example.businessLogic.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookCopyRepo extends JpaRepository<BookCopy, Long> {

    @Query(value = "SELECT * FROM book_copy WHERE status = 0", nativeQuery = true)
    List<BookCopy> availableCopies();

    List<BookCopy> findByTitle(String name);
}