package pl.podles.customerservice;

public class CustomerFoundException extends Exception{
    public CustomerFoundException(String username) {
        super("Customer with username " + username +" exist.");
    }
}
