package pl.podles.bookservice.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podles.bookservice.BookNotFoundException;
import pl.podles.bookservice.model.Book;
import pl.podles.bookservice.service.BookServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        Optional<Book> optionalBook = bookService.getBook(isbn);
        return optionalBook.isPresent()
                ? ResponseEntity.ok(optionalBook.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity editBook(@PathVariable String isbn, @RequestBody Book book) {
        try {
            bookService.editBook(isbn, book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{title}")
    public ResponseEntity getByTitle(@PathVariable String title){
        try {
            return ResponseEntity.ok(bookService.getByTitle(title));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
