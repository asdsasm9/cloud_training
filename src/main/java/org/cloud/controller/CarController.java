package org.cloud.controller;

import org.cloud.entity.Car;
import org.cloud.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        LOGGER.info("Find all cars");
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") int carId) {
        LOGGER.info("Find car by id {}", carId);
        Optional<Car> car = carService.findById(carId);
        if (car.isPresent()){
            return ResponseEntity.ok(car.get());
        }
        return ResponseEntity.notFound().build();
    }
}
