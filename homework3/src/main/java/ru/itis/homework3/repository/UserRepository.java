package ru.itis.homework3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.homework3.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
