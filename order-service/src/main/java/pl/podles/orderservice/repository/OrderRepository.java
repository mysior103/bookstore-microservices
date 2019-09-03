package pl.podles.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.podles.orderservice.model.Customer;
import pl.podles.orderservice.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByUsername(String username);
}
