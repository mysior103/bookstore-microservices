package pl.podles.customerservice.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.podles.bookstore.shared.exception.NoAccessException;
import pl.podles.bookstore.user.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ApplicationUserService applicationUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, ApplicationUserService applicationUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.applicationUserService = applicationUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void createCustomerAccount(CustomerWithPasswordDTO customerWithPasswordDTO) throws UserExistsException {
        if (applicationUserService.checkIfUserExist(customerWithPasswordDTO.getUsername())) {
            throw new UserExistsException(customerWithPasswordDTO.getUsername());
        }
        customerWithPasswordDTO.setPassword(bCryptPasswordEncoder.encode(customerWithPasswordDTO.getPassword()));
        Customer customer = CustomerMapper.ToEntity(customerWithPasswordDTO);
        applicationUserService.saveNewUser(customer.getApplicationUser());
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByUsername(String username) throws UserNotFoundException {
        ApplicationUser user = applicationUserService.getUser(username).orElseThrow(() -> new UserNotFoundException(username));
        return customerRepository.findByApplicationUser(user).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public void updateCustomer(String username, CustomerWithPasswordDTO customerWithPasswordDTO) throws UserNotFoundException, NoAccessException {
        if (applicationUserService.getLoggedUsername().equals(username) || applicationUserService.getLoggedRole().equals(UserRoleEnum.ADMIN)) {
            String id = findCustomerByUsername(username).get_id();
            Customer customer = CustomerMapper.ToEntity(customerWithPasswordDTO);
            customer.set_id(id);
            customerRepository.save(customer);
        } else {
            throw new NoAccessException();
        }


    }


}
