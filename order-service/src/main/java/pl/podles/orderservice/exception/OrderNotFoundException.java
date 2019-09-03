package pl.podles.orderservice.exception;

public class OrderNotFoundException extends Exception {


    public OrderNotFoundException(String id) {
        super("Order with id " + id + " not found.");
    }
}
