package pl.podles.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    String _id;
    LocalDateTime orderDate;
    LocalDateTime lastChangeDate;
    List<String> isbns;
    String username;
    OrderStatusEnum orderStatus;
}
