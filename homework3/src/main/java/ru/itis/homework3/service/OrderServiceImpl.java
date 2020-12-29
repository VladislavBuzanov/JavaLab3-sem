package ru.itis.homework3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.homework3.model.Order;
import ru.itis.homework3.model.Product;
import ru.itis.homework3.model.Status;
import ru.itis.homework3.model.User;
import ru.itis.homework3.repository.OrderRepository;
import ru.itis.homework3.repository.ProductRepository;
import ru.itis.homework3.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private Product getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        return productOptional.get();
    }

    private User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


    @Override
    public Order createOrder(Long userId) {
        return orderRepository.save(Order.builder()
                .consumer(getUser(userId))
                .build());
    }

    @Override
    public Order confirmOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            order.get().setStatus(Status.Confirmed);
            orderRepository.save(order.get());
            return order.get();
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public Order cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            order.get().setStatus(Status.Cancelled);
            orderRepository.save(order.get());
            return order.get();
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public Order addProduct(Long orderId, Long productId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            order.get().getProducts().add(getProduct(productId));
            orderRepository.save(order.get());
            return order.get();
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
