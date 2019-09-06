package pl.podles.bookservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.podles.bookservice.BookNotFoundException;
import pl.podles.bookservice.model.Book;
import pl.podles.bookservice.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private RestTemplate restTemplate;

    public BookServiceImpl(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllByIsbn(List<String> isbns) {
        List<Book> bookList = new ArrayList<>();
        for (String isbn : isbns) {
            Optional<Book> b = bookRepository.findByIsbn(isbn);
            b.ifPresent(bookList::add);
        }
        return bookList;
    }

    @Override
    public void editBook(String isbn, Book book) throws Exception {
        Book bookFromDB = bookRepository.findByIsbn(isbn).orElseThrow(() -> new Exception("Cannot find the book"));
        book.set_id(bookFromDB.get_id());
        bookRepository.save(book);
    }

    @Override
    public Book getByTitle(String title) throws BookNotFoundException {
        return bookRepository.findByTitle(title).orElseThrow(()-> new BookNotFoundException(title));
    }
}
