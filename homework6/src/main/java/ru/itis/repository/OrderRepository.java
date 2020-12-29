package ru.itis.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.homework3.model.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
