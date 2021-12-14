package com.aacademy.advanceacademy.dto;

import com.aacademy.advanceacademy.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

//    private Set<Car> car;
}
