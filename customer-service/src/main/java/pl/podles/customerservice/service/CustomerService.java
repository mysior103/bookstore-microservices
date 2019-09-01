package pl.podles.customerservice.service;


import pl.podles.bookstore.shared.exception.NoAccessException;
import pl.podles.bookstore.user.UserExistsException;
import pl.podles.bookstore.user.UserNotFoundException;

public interface CustomerService {
    void createCustomerAccount(CustomerWithPasswordDTO customerWithPasswordDTO) throws UserExistsException;

    Customer findCustomerByUsername(String username) throws UserNotFoundException;

    void updateCustomer(String username, CustomerWithPasswordDTO customerWithPasswordDTO) throws UserNotFoundException, NoAccessException;

}
