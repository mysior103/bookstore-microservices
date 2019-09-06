package pl.podles.orderservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.podles.orderservice.model.Book;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${url.book-service}")
    private String bookUrl;

    public static Map<String, Book> bookCache = new HashMap<>();

    @HystrixCommand(fallbackMethod = "getFallbackBook",
            commandProperties = {
                    @HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name= "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name= "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name= "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            })

    public Book getBookByIsbn(String isbn) {
        Book bookResponse = restTemplate.getForObject(bookUrl + "/" + isbn, Book.class);
        bookCache.put(isbn,bookResponse);
        return bookResponse;
    }

    public Book getFallbackBook(String isbn){
        return bookCache.get(isbn);
    }
}
