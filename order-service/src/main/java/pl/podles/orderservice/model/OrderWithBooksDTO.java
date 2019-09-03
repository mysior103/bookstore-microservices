package pl.podles.orderservice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderWithBooksDTO {
    String id;
    LocalDateTime orderDate;
    LocalDateTime lastChangeDate;
    List<Book> books;
    String username;
    OrderStatusEnum orderStatus;
}
