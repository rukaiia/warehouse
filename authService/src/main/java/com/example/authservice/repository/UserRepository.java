package com.example.authservice.repository;

import com.example.authservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUsername(String username);
    Stream<User> streamAllBy();

}
