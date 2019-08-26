package pl.podles.bookservice.model;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Book {
    private String Id;
    private String title;
    private String[] authors;
    private LocalDate releaseDate;
    private Integer numberOfPages;
    private String ISBN;
    private boolean availability;
}
