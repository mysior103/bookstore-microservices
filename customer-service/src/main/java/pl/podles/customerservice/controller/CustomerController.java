package pl.podles.customerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.podles.customerservice.model.CustomerWithPasswordDTO;

@RestController
public class CustomerController {
    @PutMapping("/{username}")
    public ResponseEntity update(@PathVariable String username, @RequestBody CustomerWithPasswordDTO customerWithPasswordDTO) {
        try {
            customerService.updateCustomer(username, customerWithPasswordDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NoAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
