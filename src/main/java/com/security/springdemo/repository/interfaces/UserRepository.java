package com.security.springdemo.repository.interfaces;

import com.security.springdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findById(Integer id);
}
