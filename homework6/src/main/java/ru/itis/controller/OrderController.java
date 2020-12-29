package ru.itis.homework3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.homework3.service.OrderService;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(value = "/order/{order-id}/cancel", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> cancel(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(
                EntityModel.of(orderService.cancelOrder(orderId)));
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> create(@RequestParam("user-id") Long userId) {
        return ResponseEntity.ok(
                EntityModel.of(orderService.createOrder(userId)));
    }

    @RequestMapping(value = "/order/{order-id}/addProduct/{product-id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> addProduct(@PathVariable("order-id") Long orderId, @PathVariable("product-id") Long productId) {
        return ResponseEntity.ok(
                EntityModel.of(orderService.addProduct(orderId, productId)));
    }

    @RequestMapping(value = "/order/{order-id}/confirm", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> confirm(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(
                EntityModel.of(orderService.confirmOrder(orderId)));
    }

}
