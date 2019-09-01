package pl.podles.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Book {

    @Id
    String _id;
    String title;
    String ISBN;
}
