package pl.podles.customerservice.service;


import pl.podles.customerservice.CustomerFoundException;
import pl.podles.customerservice.CustomerNotFoundException;
import pl.podles.customerservice.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findCustomerByUsername(String username) throws CustomerNotFoundException;

    void updateCustomer(Customer customer) throws CustomerNotFoundException;

    void createCustomer(Customer customer) throws CustomerFoundException;

    Customer getCustomer(String username) throws CustomerNotFoundException;
}
