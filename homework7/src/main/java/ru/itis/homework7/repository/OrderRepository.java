package ru.itis.homework7.repository;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import ru.itis.homework7.model.Order;
import ru.itis.homework7.model.QOrder;

public interface OrderRepository extends MongoRepository<Order, String>, QuerydslPredicateExecutor<Order>, QuerydslBinderCustomizer<QOrder> {
    @Override
    default void customize(QuerydslBindings bindings, QOrder qOrder) {
        bindings.bind(qOrder.products.any().name).as("product.name").first(
                StringExpression::containsIgnoreCase
        );
    }
}
