package pl.podles.customerservice.model;

import lombok.Data;

@Data
public class CustomerDTO {
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    String email;
    String username;
}
