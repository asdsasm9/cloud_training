package org.cloud.car;

import lombok.NonNull;
import org.cloud.car.dto.CarCreateDTO;
import org.cloud.car.dto.CarTimeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(long carId) {
        return carRepository.findById(carId);
    }

    public Car create(@NonNull CarCreateDTO carCreateDTO) {
        LOGGER.info("create called {}", carCreateDTO);
        Car car = new Car();
        car.setStartTime(calcLocalTime(carCreateDTO.getStartTime()));
        car.setEndTime(calcLocalTime(carCreateDTO.getEndTime()));
        car.setLicencePlate(carCreateDTO.getLicencePlate());
        return carRepository.save(car);
    }

    private LocalTime calcLocalTime(CarTimeDTO carTimeDTO) {
        return LocalTime.of(carTimeDTO.getHours(), carTimeDTO.getMinutes(), carTimeDTO.getSeconds(), carTimeDTO.getNano());
    }

    public Car update(@NotNull Car car){
        return carRepository.save(car);
    }

    public void delete(@NonNull long id){
        carRepository.deleteById(id);
    }



}