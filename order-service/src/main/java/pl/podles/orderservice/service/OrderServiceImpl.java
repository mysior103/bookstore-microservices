package pl.podles.orderservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.podles.orderservice.exception.OrderNotFoundException;
import pl.podles.orderservice.mapper.OrderMapper;
import pl.podles.orderservice.model.*;
import pl.podles.orderservice.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${url.book-service}")
    private String bookUrl;

    @Value("${url.customer-service}")
    private String customerUrl;

    @Override
    public void addNewOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setIsbns(orderDTO.getBooksISBN());
        order.setUsername(orderDTO.getCustomerUsername());
        order.setOrderStatus(OrderStatusEnum.NEW);
        order.setLastChangeDate(LocalDateTime.now());
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public List<OrderWithBooksDTO> getAllByUsername(String username) {
        List<Order> allOrders = orderRepository.findAllByUsername(username);
        List<OrderWithBooksDTO> orderWithBooksDTOS = new ArrayList<>();

        for (Order order : allOrders) {
            List<Book> books = order.getIsbns().stream().map(this::getBookByIsbn).collect(Collectors.toList());
            orderWithBooksDTOS.add(OrderMapper.toDtoWithBooks(order, books));
        }
        return orderWithBooksDTOS;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderWithBooksAndCustomerDTO> getAllWithDetails() {
        List<Order> allOrders = orderRepository.findAll();
        List<OrderWithBooksAndCustomerDTO> orderWithBooksAndCustomerDTOS = new ArrayList<>();
        for(Order o : allOrders){
            List<Book> books = o.getIsbns().stream().map(this::getBookByIsbn).collect(Collectors.toList());
            Customer customer = getCustomer(o.getUsername());
            orderWithBooksAndCustomerDTOS.add(OrderMapper.toDtoWithBooksAndCustomer(o,books,customer));
        }
        return orderWithBooksAndCustomerDTOS;
    }

    @Override
    public void updateStatus(String id, OrderStatusEnum status) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setOrderStatus(status);
        order.setLastChangeDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    private Book getBookByIsbn(String isbn) {
        return restTemplate.getForObject(bookUrl + "/" + isbn, Book.class);
    }

    private Customer getCustomer(String username){
        return restTemplate.getForObject(customerUrl + "/" + username, Customer.class);
    }

}
