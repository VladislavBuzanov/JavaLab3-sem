package ru.itis.homework3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.itis.homework3.model.Address;
import ru.itis.homework3.model.Order;
import ru.itis.homework3.model.Product;
import ru.itis.homework3.model.User;
import ru.itis.homework3.repository.AddressRepository;
import ru.itis.homework3.repository.OrderRepository;
import ru.itis.homework3.repository.ProductRepository;

import java.util.Arrays;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        MongoClient client = MongoClients.create();
        MongoTemplate mongoTemplate = new MongoTemplate(client, "test");
        AddressRepository addressRepository = run.getBean(AddressRepository.class);
        OrderRepository orderRepository = run.getBean(OrderRepository.class);
        ProductRepository productRepository = run.getBean(ProductRepository.class);

        Address address = Address.builder()
                .address("addr")
                .city("city")
                .build();
        addressRepository.save(address);

        User user = User.builder()
                .name("name")
                .build();
        mongoTemplate.save(user);

        Product product = Product.builder()
                .name("product1")
                .price(100)
                .build();
        productRepository.save(product);
        Product product2 = Product.builder()
                .name("product2")
                .price(150)
                .build();
        productRepository.save(product2);

        Order order = Order.builder()
                .address(address)
                .consumer(user)
                .products(Arrays.asList(product, product2)).build();
        orderRepository.save(order);
    }

}
