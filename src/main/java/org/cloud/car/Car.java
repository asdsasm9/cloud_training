package org.cloud.car;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private LocalTime startTime;
    @NonNull
    private LocalTime endTime;
    @NonNull
    private String licencePlate;
}
