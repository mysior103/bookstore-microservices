package pl.podles.orderservice.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderWithBooksAndCustomerDTO {
    String id;
    LocalDateTime orderDate;
    LocalDateTime lastChangeDate;
    List<Book> books;
    Customer customer;
    OrderStatusEnum orderStatus;

}
