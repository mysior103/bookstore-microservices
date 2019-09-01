package pl.podles.customerservice.model;

import lombok.Data;

@Data
public class CustomerWithPasswordDTO {
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    String email;
    String username;
    String password;
}
