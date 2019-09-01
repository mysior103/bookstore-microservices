package pl.podles.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Document
@Data
public class Customer {
    @Id
    String _id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String address;
    @NotBlank
    String phoneNumber;
    @Email
    String email;
    @NotBlank
    String username;
}
