package pl.podles.coreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.podles.coreservice.model.UserRoleEnum;
import pl.podles.coreservice.service.ApplicationUserService;

@RestController
public class CustomerController {

    private ApplicationUserService applicationUserService;
    private CustomerService customerService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public CustomerController(ApplicationUserService applicationUserService, CustomerService customerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserService = applicationUserService;
        this.customerService = customerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up-customer")
    public ResponseEntity<Void> signUp(@RequestBody CustomerDtoWithPassword customerDtoWithPassword) {
        customerDtoWithPassword.setPassword(bCryptPasswordEncoder.encode(customerDtoWithPassword.getPassword()));
        Customer customer = CustomerMapper.ToEntity(customerDtoWithPassword);
        if (applicationUserService.checkIfUserExist(customerDtoWithPassword.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        applicationUserService.saveNewUser(customer.getApplicationUser());
        customerService.createCustomerAccount(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-logged-in-user")
    public UserRoleEnum getLoggedInRole()
    {
        return applicationUserService.getLoggedRole();
    }
}
