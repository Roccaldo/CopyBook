package com.example.businessLogic.services;

import com.example.businessLogic.entities.Book;
import com.example.businessLogic.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;


    public Book create(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Optional<Book> findById(Long id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional;
        } else {
            return Optional.empty();
        }
    }

    public Optional<Book> update(Long id, Book book) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookOptional.get().setAuthor(book.getAuthor());
            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setYear(book.getYear());
            bookOptional.get().setStatus(book.getStatus());
            bookRepo.save(bookOptional.get());
            return bookOptional;
        } else {
            return Optional.empty();
        }
    }

    public Optional<Book> delete(Long id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookRepo.delete(bookOptional.get());
            return bookOptional;
        } else {
            return Optional.empty();
        }
    }


}
