package org.cloud.controller;

import org.cloud.entity.Car;
import org.cloud.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Car>> listAllFileTypes() {
        LOGGER.info("List all cars");
        return ResponseEntity.ok(carService.findAll());
    }

}
