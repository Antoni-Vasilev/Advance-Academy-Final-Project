package com.aacademy.advanceacademy.service;

import com.aacademy.advanceacademy.model.Car;

import java.util.Set;

public interface CarService {

    Car findByBrand(String brand);

    Car findById(Long id);

    Car findByModel(String model);

    Car save(Car car);

    Car update(Car car, Long id);

    void deleteById(Long id);

    Set<Car> findAll();
}
