package com.manpower.service;

import com.manpower.model.User;
import com.manpower.model.dto.AuthenticateRequest;
import com.manpower.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setCreateDate(updatedUser.getCreateDate());
            user.setUpdateDate(updatedUser.getUpdateDate());
            user.setCompany(updatedUser.getCompany());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<User> authenticate(AuthenticateRequest authenticate) {
        return userRepository.findByUsernameAndPassword(authenticate.getUserName(),authenticate.getPassword());
    }
}
