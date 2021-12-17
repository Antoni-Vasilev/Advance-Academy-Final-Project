package com.aacademy.advanceacademy.service.Impl;

import com.aacademy.advanceacademy.exception.ResourceNotFoundException;
import com.aacademy.advanceacademy.model.User;
import com.aacademy.advanceacademy.repository.UserRepository;
import com.aacademy.advanceacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d does not exits.", id)));
    }

    @Override
    public Set<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User update(User user, Long id) {
        User foundUser = findById(id);
        User updatedUser = User.builder()
                .id(foundUser.getId())
                .age(user.getAge())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();

        return save(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
