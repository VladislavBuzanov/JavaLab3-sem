package ru.itis.homework3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework3.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
