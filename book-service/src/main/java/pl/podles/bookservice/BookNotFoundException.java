package pl.podles.bookservice;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String title) {
        super("Book " + title + " not found!");
    }
}
