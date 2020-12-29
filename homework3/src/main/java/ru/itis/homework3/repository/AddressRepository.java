package ru.itis.homework3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.homework3.model.Address;


public interface AddressRepository extends CrudRepository<Address, Long> {
}
