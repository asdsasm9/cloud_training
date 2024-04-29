//package org.cloud;
//
//import org.cloud.car.Car;
//import org.cloud.car.CarService;
//import org.cloud.mapping.BeanMappingService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class CarServiceTest {
//
//    @Mock
//    private BeanMappingService beanMappingService;
//
//    @Autowired
//    private CarService bookService;
//    @Test
//    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
//        List<Car> books = bookService.findAll();
//
//        Assert.assertEquals(books.size(), 0);
//    }
//}
