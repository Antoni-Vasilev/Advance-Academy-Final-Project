package com.aacademy.advanceacademy.controller;

import com.aacademy.advanceacademy.converter.UserConverter;
import com.aacademy.advanceacademy.dto.UserDto;
import com.aacademy.advanceacademy.model.User;
import com.aacademy.advanceacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping
    public ResponseEntity<Set<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(userConverter::toUserDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userConverter.toUserDto(userService.findById(id)));
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userConverter.toUserDto(userService.findByEmail(email)));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        User user = userConverter.toUser(userDto);
        User saveUser = userService.save(user);
        return ResponseEntity.ok(userConverter.toUserDto(saveUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<HttpStatus> deleteByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto, @PathVariable Long id) {
        User user = userConverter.toUser(userDto);
        User update = userService.update(user, id);
        return ResponseEntity.ok(userConverter.toUserDto(update));
    }
}
