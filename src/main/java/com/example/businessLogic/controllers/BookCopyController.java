package com.example.businessLogic.controllers;

import com.example.businessLogic.entities.BookCopy;
import com.example.businessLogic.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookCopyController {

    @Autowired
    private BookCopyService bookCopyService;

    @PostMapping("/books/{id}/copies")
    public ResponseEntity<Optional<BookCopy>> createCopy(@PathVariable Long id) {
        Optional<BookCopy> book = bookCopyService.createCopy(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/books/copies/{id}/loan")
    public ResponseEntity<Optional<BookCopy>> loanCopy(@PathVariable Long id) {
        Optional<BookCopy> book = bookCopyService.loanCopyBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/books/copies/{id}/return")
    public ResponseEntity<Optional<BookCopy>> returnCopy(@PathVariable Long id) {
        Optional<BookCopy> book = bookCopyService.returnCopyBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/copiedBooks")
    public ResponseEntity<List<BookCopy>> findAll() {
        return ResponseEntity.ok(bookCopyService.findAll());
    }

    @GetMapping("/books/copies/available")
    public ResponseEntity<List<BookCopy>> findAvailableCopies() {
        return ResponseEntity.ok(bookCopyService.findAvailableCopies());
    }

    @GetMapping("/books/copies/count")
    public ResponseEntity<Long> countAvailableCopies(@RequestParam String name) {
        return ResponseEntity.ok(bookCopyService.countAvailableCopies(name));
    }
}
