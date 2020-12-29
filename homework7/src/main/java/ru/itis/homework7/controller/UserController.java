package ru.itis.homework7.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.homework7.model.Order;
import ru.itis.homework7.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders/search")
    public ResponseEntity<List<Order>> searchByPredicate(@QuerydslPredicate(root = Order.class, bindings = OrderRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(orderRepository.findAll(predicate).spliterator(), true)
                        .collect(Collectors.toList())
        );
    }
}
