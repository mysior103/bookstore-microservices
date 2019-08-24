package pl.podles.bookservice.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podles.bookservice.model.Book;
import pl.podles.bookservice.service.BookServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping
    public ResponseEntity<Book> getBook(String id) {
        Optional<Book> optionalBook =  bookService.getBook(id);
        return optionalBook.isPresent()
                ? ResponseEntity.ok(optionalBook.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

}
