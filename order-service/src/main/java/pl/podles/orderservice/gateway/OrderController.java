package pl.podles.orderservice.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.podles.orderservice.model.Order;

@RestController("/order")
public class OrderController {

    @GetMapping("/all")
    public ResponseEntity<Order> getOrderByCustomerUsername(@PathVariable String username){

        return null;
    }

}
