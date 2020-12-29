package ru.itis.homework7.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.homework7.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
