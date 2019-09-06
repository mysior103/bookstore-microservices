package pl.podles.bookservice.service;

import pl.podles.bookservice.BookNotFoundException;
import pl.podles.bookservice.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBook(String id);

    void saveBook(Book book) throws Exception;

    List<Book> getAllByIsbn(List<String> isbns);

    void editBook(String isbn, Book book) throws Exception;

    Book getByTitle(String title) throws BookNotFoundException;
}