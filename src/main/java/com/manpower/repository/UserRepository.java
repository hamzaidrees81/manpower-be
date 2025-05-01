package com.manpower.repository;

import com.manpower.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  Optional<User> findByUsernameIgnoreCaseAndPassword(String userName, String password);
}
