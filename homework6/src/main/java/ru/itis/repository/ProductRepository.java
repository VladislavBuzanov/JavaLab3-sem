package ru.itis.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.homework3.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
