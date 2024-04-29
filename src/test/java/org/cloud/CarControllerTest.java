package org.cloud;

import org.cloud.car.Car;
import org.cloud.car.CarController;
import org.cloud.car.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    private Car dummy1;
    private Car dummy2;
    @BeforeEach
    public void initData(){
        dummy1 = getCar(1);
        dummy2 = getCar(2);
    }

    private Car getCar(long id){
        Car newCar = new Car();
        newCar.setLicencePlate(String.valueOf(id));
        newCar.setEndTime(LocalTime.MIDNIGHT);
        newCar.setEndTime(LocalTime.NOON);
        return newCar;
    }

    @Test
    public void findAll() throws Exception {
        when(carService.findAll()).thenReturn(asList(dummy1, dummy2));

        mockMvc.perform(get("/car/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
