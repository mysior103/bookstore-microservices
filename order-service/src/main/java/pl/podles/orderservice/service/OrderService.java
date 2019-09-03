package pl.podles.orderservice.service;


import pl.podles.orderservice.exception.OrderNotFoundException;
import pl.podles.orderservice.model.*;

import java.util.List;

public interface OrderService {
    void addNewOrder(OrderDTO orderDTO);

    List<OrderWithBooksDTO> getAllByUsername(String username);

    List<Order> getAll();

    void updateStatus(String id, OrderStatusEnum status) throws OrderNotFoundException;

    List<OrderWithBooksAndCustomerDTO> getAllWithDetails();
}
