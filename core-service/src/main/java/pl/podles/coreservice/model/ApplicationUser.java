package pl.podles.coreservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ApplicationUser {
    @Id
    String Id;
    String username;
    String password;
    UserRoleEnum userRole;
}
