package pl.podles.customerservice;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String username) {
        super("Could not find username: " + username);
    }
}
