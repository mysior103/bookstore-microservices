package pl.podles.orderservice.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    String customerUsername;
    List<String> booksISBN;
}
