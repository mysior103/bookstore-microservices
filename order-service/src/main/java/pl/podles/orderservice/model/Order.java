package pl.podles.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class Order {
    @Id
    String _id;
    LocalDateTime orderDate;
    LocalDateTime lastChangeDate;
    @DBRef
    List<Book> books;
    @DBRef
    Customer customer;
    OrderStatusEnum orderStatus;
}
