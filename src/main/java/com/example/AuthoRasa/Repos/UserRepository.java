package com.example.AuthoRasa.Repos;

import com.example.AuthoRasa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// User class , PK : id
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email,String password);
}
