package ru.itis.homework3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {
    @Id
    private Long id;
    @DBRef
    private User consumer;
    @DBRef
    private Address address;
    @DBRef
    private List<Product> products;
    private Status status;
}
