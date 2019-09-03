package pl.podles.customerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podles.customerservice.CustomerFoundException;
import pl.podles.customerservice.CustomerNotFoundException;
import pl.podles.customerservice.model.Customer;
import pl.podles.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
        } catch (CustomerFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity update(@RequestBody Customer customer) {
        try {
            customerService.updateCustomer(customer);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity getCustomer(@PathVariable String username) {
        try {
            return ResponseEntity.ok(customerService.getCustomer(username));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
