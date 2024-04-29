package org.cloud.car.dto;

import lombok.Getter;

@Getter
public class CarTimeDTO {
    private int hours;
    private int minutes;
    private int seconds;
    private int nano;
}
