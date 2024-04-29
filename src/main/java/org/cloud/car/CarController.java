package org.cloud.car;

import org.cloud.car.dto.CarCreateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    public final String CAR_NOT_EXISTING = "Car doesn't exist in the database";
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
        if (car.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CAR_NOT_EXISTING);
        }
        return ResponseEntity.ok(car.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody CarCreateDTO carCreateDTO) {
        return carService.create(carCreateDTO).getId();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@NotNull @RequestBody Car resource) {
        if (carService.findById(resource.getId()).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CAR_NOT_EXISTING);
        }
        carService.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        if (carService.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CAR_NOT_EXISTING);
        }
        carService.delete(id);
    }
}
