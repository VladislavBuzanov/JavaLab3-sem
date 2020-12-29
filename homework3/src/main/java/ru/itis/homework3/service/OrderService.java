package ru.itis.homework3.service;


import ru.itis.homework3.model.Order;

public interface OrderService {
    Order createOrder(Long userId);
    Order confirmOrder(Long orderId);
    Order cancelOrder(Long orderId);
    Order addProduct(Long orderId, Long productId);
}
