package com.aacademy.advanceacademy.service.Impl;

import com.aacademy.advanceacademy.exception.ResourceNotFoundException;
import com.aacademy.advanceacademy.model.Car;
import com.aacademy.advanceacademy.repository.CarRepository;
import com.aacademy.advanceacademy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Car with id %d does not exits.", id)));
    }

    @Override
    public Car findByModel(String model) {
        return carRepository.findByModel(model);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car, Long id) {
        Car foundCar = findById(id);
        Car updatedCar = Car.builder()
                .id(foundCar.getId())
                .brand(car.getBrand())
                .color(car.getColor())
                .model(car.getModel())
                .price(car.getPrice())
                .build();
        return save(updatedCar);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Set<Car> findAll() {
        return new HashSet<>(carRepository.findAll());
    }
}
