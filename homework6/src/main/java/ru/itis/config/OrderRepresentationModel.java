package ru.itis.homework3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.homework3.controller.OrderController;
import ru.itis.homework3.model.Order;
import ru.itis.homework3.model.Status;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class OrderRepresentationModel implements RepresentationModelProcessor<EntityModel<Order>> {

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        Order order = model.getContent();
        if (order != null && order.getStatus().equals(Status.Created)) {
            model.add(linkTo(methodOn(OrderController.class)
                    .confirm(order.getId())).withRel("confirm"));
            model.add(linkTo(methodOn(OrderController.class)
                    .cancel(order.getId())).withRel("cancel"));
            model.add(linkTo(methodOn(OrderController.class)
                    .addProduct(order.getId(), null)).withRel("addProduct"));

        }
        return model;
    }


}
