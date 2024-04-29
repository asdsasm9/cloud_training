package org.cloud;

import org.cloud.entity.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {


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



//        Book book1 = new Book();
//        book1.setName("Harry Potter");
    }
}