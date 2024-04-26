package org.cloud;

import org.cloud.entity.Book;
import org.cloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@SpringBootApplication
public class Main {

    private final BookService bookService;

    private static final CarsCounter carsCounter = new CarsCounter(FileHandler.loadCars());

    @Autowired
    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

//        exercise2();
//        exercise3();
//        exercise4();
//        exercise5();
//        exercise6();
    }

//    @Bean
//    @Profile("!test && !server")
//    public CommandLineRunner commandLineRunner() {
//        return args -> dataLoading();
//    }
//
//    public void dataLoading() {
//        Book book1 = new Book();
//        book1.setName("Harry Potter");
//    }
    private static void exercise2(){
        int totalCars = carsCounter.getTotalCars();

        out.println("Exercise 2");
        out.printf("The data of %d vehicles were recorded in the measurement %n", totalCars);
    }

    private static void exercise3(){
        LocalTime nineOClock = LocalTime.of(9, 0, 0);
        int totalCarsBeforeNine = carsCounter.totalCarsExitedBefore(nineOClock);

        out.println("\nExercise 3");
        out.printf("Before 9 o'clock %d vehicles passed the exit point recorder.%n", totalCarsBeforeNine);
    }

    private static void exercise4() {
        out.println("\nExercise 4");

        Scanner scanner = new Scanner(in);

        out.println("Enter an hour and minute value: ");
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        scanner.close();

        LocalTime inputTime = LocalTime.of(hours, minutes, 0);
        int totalCarsInputTime = carsCounter.totalCarsPassedEntrypoint(inputTime);
        float trafficIntensity = carsCounter.getTrafficIntensity(inputTime);

        out.printf("a. The number of vehicle that passed the entry point recorder: %d %n", totalCarsInputTime);
        out.printf("b. The traffic intensity: %.1f%n", trafficIntensity);
    }

    private static void exercise5() {
        out.println("\nExercise 5");
        out.println("The data of the vehicle with the highest speed are ");

        Car fastestCar = carsCounter.getFastestCar();
        out.printf("license plate number: %s%n", fastestCar.getLicencePlate());
        out.printf("average speed: %d km/h%n", fastestCar.getAvgSpeed());

        int overtakenCarsByCar = carsCounter.totalOfOvertakenCarsByCar(fastestCar);
        out.printf("number of overtaken vehicles: %d%n", overtakenCarsByCar);
    }

    private static void exercise6() {
        out.println("\nExercise 6");

        int speedLimit = 90;
        int totalSpeedingCars = carsCounter.numOfSpeedingCars(speedLimit);
        int totalCars = carsCounter.getTotalCars();
        double percentage = (((float) totalSpeedingCars / (float) totalCars) * 100);
        out.printf("%.2f%% percent of the vehicles were speeding.%n", percentage);
    }

}