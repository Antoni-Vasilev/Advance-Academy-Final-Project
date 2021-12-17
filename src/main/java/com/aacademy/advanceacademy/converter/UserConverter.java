package com.aacademy.advanceacademy.converter;

import com.aacademy.advanceacademy.dto.UserDto;
import com.aacademy.advanceacademy.exception.ResourceNotFoundException;
import com.aacademy.advanceacademy.model.User;
import com.aacademy.advanceacademy.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final UserRepository userRepository;

    public UserConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User toUser(UserDto userDto) {
        return userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d does not exits.", userDto.getId())));
    }
}
