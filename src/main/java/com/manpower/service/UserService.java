package com.manpower.service;

import com.manpower.mapper.UserMapper;
import com.manpower.model.User;
import com.manpower.model.dto.AuthenticateRequest;
import com.manpower.model.dto.UserDTO;
import com.manpower.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User getUserByIdRaw(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public Optional<UserDTO> getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDTO);
    }

    public UserDTO createUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Integer id, UserDTO updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setPassword(updatedUser.getPassword());
            return userMapper.toDTO(userRepository.save(user));
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<User> authenticate(AuthenticateRequest authenticate) {
        return userRepository.findByUsernameIgnoreCaseAndPassword(authenticate.getEmail(),authenticate.getPassword());
    }
}
