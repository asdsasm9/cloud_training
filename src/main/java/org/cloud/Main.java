package org.cloud;

import org.cloud.entity.Car;
import org.cloud.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class Main {

    private final CarService carService;
    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    public Main(CarService carService) {
        this.carService = carService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner commandLineRunner() {
        return args -> dataLoading();
    }

    public void dataLoading() {
        List<Car> cars = FileHandler.loadCars();

        for (Car car : cars) {
            carService.save(car);
        }
        LOGGER.info("Initialization of cars data finished");
    }
}