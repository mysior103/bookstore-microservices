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

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate, BookService bookService) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.bookService = bookService;
    }


    private BookService bookService;


    @Value("${url.customer-service}")
    private String customerUrl;

    @Override
    public void addNewOrder(OrderDTO orderDTO) {
        var order = new Order();
        order.setIsbns(orderDTO.getBooksISBN());
        order.setUsername(orderDTO.getCustomerUsername());
        order.setOrderStatus(OrderStatusEnum.NEW);
        order.setLastChangeDate(LocalDateTime.now());
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public List<OrderWithBooksDTO> getAllByUsername(String username) {
        var allOrders = orderRepository.findAllByUsername(username);
        var orderWithBooksDTOS = new ArrayList<OrderWithBooksDTO>();

        for (var order : allOrders) {
            var books = order.getIsbns().stream().map(isbn -> bookService.getBookByIsbn(isbn)).collect(Collectors.toList());
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
        var allOrders = orderRepository.findAll();
        var orderWithBooksAndCustomerDTOS = new ArrayList<OrderWithBooksAndCustomerDTO>();
        for (var o : allOrders) {
            orderWithBooksAndCustomerDTOS
                    .add(OrderMapper.toDtoWithBooksAndCustomer(o,
                            o.getIsbns().stream().map(isbn -> bookService.getBookByIsbn(isbn)).collect(Collectors.toList()),
                            getCustomer(o.getUsername())));
        }
        return orderWithBooksAndCustomerDTOS;
    }

    @Override
    public void updateStatus(String id, OrderStatusEnum status) throws OrderNotFoundException {
        var order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setOrderStatus(status);
        order.setLastChangeDate(LocalDateTime.now());
        orderRepository.save(order);
    }


    private Customer getCustomer(String username) {
        return restTemplate.getForObject(customerUrl + "/" + username, Customer.class);
    }


}
