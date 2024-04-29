package org.cloud;

import org.cloud.car.Car;
import org.cloud.car.CarRepository;
import org.cloud.car.CarService;
import org.cloud.car.dto.CarCreateDTO;
import org.cloud.car.dto.CarTimeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CarServiceTest {

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarService carService;
    private Car dummy1;
    private Car dummy2;

    private Car getCar(long id){
        Car newCar = new Car();
        newCar.setLicencePlate(String.valueOf(id));
        newCar.setEndTime(LocalTime.MIDNIGHT);
        newCar.setStartTime(LocalTime.NOON);
        return newCar;
    }
    private CarCreateDTO getCarCreateDTO(long id){
        CarCreateDTO newCarDTO = new CarCreateDTO();
        newCarDTO.setLicencePlate(String.valueOf(id));
        newCarDTO.setStartTime(getTimeDTO(1));
        newCarDTO.setEndTime(getTimeDTO(2));
        return newCarDTO;
    }

    private CarTimeDTO getTimeDTO(int num){
        CarTimeDTO time = new CarTimeDTO();
        time.setHours(num);
        time.setMinutes(num);
        time.setSeconds(num);
        time.setNano(num);
        return time;
    }
    @Test
    public void findById() {
        dummy1 = getCar(1);
        dummy1.setId(1L);
        when(carRepository.findById(dummy1.getId())).thenReturn(Optional.ofNullable(dummy1));
        Optional<Car> carOptional = carService.findById(dummy1.getId());
        Assert.assertTrue(carOptional.isPresent());
    }

    @Test
    public void findAll() {
        dummy1 = getCar(1);
        dummy2 = getCar(2);
        when(carRepository.findAll()).thenReturn(asList(dummy1, dummy2));
        List<Car> cars = carService.findAll();
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void update() {
        dummy1 = getCar(1);
        dummy2 = getCar(2);
        dummy1.setLicencePlate("change");
        carService.update(dummy1);
        verify(carRepository, times(1)).save(dummy1);
    }


}
