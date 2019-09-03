package pl.podles.orderservice.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podles.orderservice.exception.OrderNotFoundException;
import pl.podles.orderservice.model.OrderDTO;
import pl.podles.orderservice.model.OrderStatusEnum;
import pl.podles.orderservice.service.OrderService;


@RestController("/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new-order")
    public ResponseEntity addNewOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addNewOrder(orderDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{username}")
    public ResponseEntity getAllByUsername(@PathVariable String username) {
        return ResponseEntity.ok(orderService.getAllByUsername(username));
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/all/details")
    public ResponseEntity getAllWithDetails() {
        return ResponseEntity.ok(orderService.getAllWithDetails());
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity updateStatus(@PathVariable String id, @RequestParam("status") OrderStatusEnum status) {
        try {
            orderService.updateStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
