package pl.podles.customerservice.service;


import org.springframework.stereotype.Service;
import pl.podles.customerservice.CustomerFoundException;
import pl.podles.customerservice.CustomerNotFoundException;
import pl.podles.customerservice.model.Customer;
import pl.podles.customerservice.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findCustomerByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        Customer customerFromDB = findCustomerByUsername(customer.getUsername()).orElseThrow(() -> new CustomerNotFoundException(customer.getUsername()));
        customer.set_id(customerFromDB.get_id());
        customerRepository.save(customer);
    }

    @Override
    public void createCustomer(Customer customer) throws CustomerFoundException {
        if (findCustomerByUsername(customer.getUsername()).isPresent()) {
            throw new CustomerFoundException(customer.getUsername());
        }
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String username) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = findCustomerByUsername(username);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        }
        throw new CustomerNotFoundException(username);
    }
}
