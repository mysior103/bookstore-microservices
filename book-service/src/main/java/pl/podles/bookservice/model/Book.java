package pl.podles.bookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Book {
    @JsonIgnore
    @Id
    private String _id;
    private String title;
    private List<String> authors;
    private String isbn;
    private Double price;
}
