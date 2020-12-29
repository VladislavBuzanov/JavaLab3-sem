package ru.itis.homework3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.homework3.model.Address;


public interface AddressRepository extends MongoRepository<Address, Long> {
}
