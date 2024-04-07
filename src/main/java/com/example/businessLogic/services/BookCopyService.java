package com.example.businessLogic.services;

import com.example.businessLogic.entities.Book;
import com.example.businessLogic.entities.BookCopy;
import com.example.businessLogic.entities.enums.BookStatus;
import com.example.businessLogic.repositories.BookCopyRepo;
import com.example.businessLogic.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepo bookCopyRepo;

    @Autowired
    private BookRepo bookRepo;

    /**
     * Creates a copy of a book
     *
     * @param id the id of the book
     * @return the created copy
     */
    public Optional<BookCopy> createCopy(Long id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            BookCopy copy = new BookCopy();
            copy.setYear(bookOptional.get().getYear());
            copy.setTitle(bookOptional.get().getTitle());
            copy.setAuthor(bookOptional.get().getAuthor());
            copy.setStatus(bookOptional.get().getStatus());
            bookCopyRepo.save(copy);
            return Optional.of(copy);
        } else {
            return Optional.empty();
        }
    }

    /**
     * borrow a copy of a book by choosing with id
     *
     * @param id of the book to borrow
     * @return the book borrowed
     */
    public Optional<BookCopy> loanCopyBook(Long id) {
        Optional<BookCopy> bookCopyOptional = bookCopyRepo.findById(id);
        if (bookCopyOptional.isPresent()) {
            bookCopyOptional.get().setStatus(BookStatus.INPRESTITO);
            bookCopyRepo.save(bookCopyOptional.get());
            return bookCopyOptional;
        } else {
            return Optional.empty();
        }
    }

    /**
     * return a copy of a book by choosing with id
     *
     * @param id of the book to return
     * @return the book returned
     */
    public Optional<BookCopy> returnCopyBook(Long id) {
        Optional<BookCopy> bookCopyOptional = bookCopyRepo.findById(id);
        if (bookCopyOptional.isPresent()) {
            bookCopyOptional.get().setStatus(BookStatus.DISPONIBILE);
            bookCopyRepo.save(bookCopyOptional.get());
            return bookCopyOptional;
        } else {
            return Optional.empty();
        }
    }

    public List<BookCopy> findAll() {
        return bookCopyRepo.findAll();
    }

    /**
     * finds all available copies of a book
     *
     * @return the list of available copies
     */
    public List<BookCopy> findAvailableCopies() {
        return bookCopyRepo.availableCopies();
    }

    /**
     *  finds the number of available copies of a book by title
     * @param name the title of the book
     * @return the number of available copies of the book
     */
    public Long countAvailableCopies(String name) {
        List<BookCopy> books = bookCopyRepo.findByTitle(name);
        return (long) books.size();
    }
}
