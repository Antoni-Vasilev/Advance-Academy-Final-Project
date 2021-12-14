package com.aacademy.advanceacademy.service;

import com.aacademy.advanceacademy.model.User;

import java.util.Set;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    Set<User> findAll();

    User update(User user, Long id);

    void deleteById(Long id);

    void deleteByEmail(String email);

    User save(User user);
}
