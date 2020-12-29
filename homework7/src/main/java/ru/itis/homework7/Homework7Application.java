package ru.itis.homework7;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.itis.homework7.model.Order;
import ru.itis.homework7.model.Product;
import ru.itis.homework7.model.User;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories
public class Homework7Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework7Application.class, args);

//        User user = User.builder()
//                .name("Vladislav")
//                .build();
//        User user2 = User.builder()
//                .name("Oleg")
//                .build();
//
//        Product product = Product.builder()
//                .name("TV")
//                .price(100)
//                .build();
//        Product product2 = Product.builder()
//                .name("Dog")
//                .price(100000)
//                .build();
//        Product product3 = Product.builder()
//                .name("Laptop")
//                .price(120)
//                .build();
//
//        Order order = Order.builder()
//                .consumer(user)
//                .products(Arrays.asList(product, product2))
//                .build();
//        Order order2 = Order.builder()
//                .consumer(user2)
//                .products(Arrays.asList(product2, product3))
//                .build();
//
//        MongoClient client = MongoClients.create();
//        MongoTemplate mongoTemplate = new MongoTemplate(client, "search-dsl");
//        mongoTemplate.save(user);
//        mongoTemplate.save(user2);
//        mongoTemplate.save(product);
//        mongoTemplate.save(product2);
//        mongoTemplate.save(product3);
//        mongoTemplate.save(order);
//        mongoTemplate.save(order2);
    }

}
