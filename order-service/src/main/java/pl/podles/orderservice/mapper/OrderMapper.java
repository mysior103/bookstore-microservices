package pl.podles.orderservice.mapper;

import pl.podles.orderservice.model.*;

import java.util.List;

public class OrderMapper {

    public static OrderWithBooksDTO toDtoWithBooks(Order order, List<Book> books) {
        var orderDto = new OrderWithBooksDTO();
        orderDto.setId(order.get_id());
        orderDto.setBooks(books);
        orderDto.setLastChangeDate(order.getLastChangeDate());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUsername(order.getUsername());
        return orderDto;
    }

    public static OrderWithBooksAndCustomerDTO toDtoWithBooksAndCustomer(Order order, List<Book> books, Customer customer) {
        var orderDto = new OrderWithBooksAndCustomerDTO();
        orderDto.setId(order.get_id());
        orderDto.setBooks(books);
        orderDto.setLastChangeDate(order.getLastChangeDate());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setCustomer(customer);
        return orderDto;
    }

}
