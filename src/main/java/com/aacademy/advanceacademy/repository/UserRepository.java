package com.aacademy.advanceacademy.repository;

import com.aacademy.advanceacademy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Set<User> findByEmail(String email);

    void deleteByEmail(String email);
}
