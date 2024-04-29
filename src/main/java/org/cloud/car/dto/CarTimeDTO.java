package org.cloud.car.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarTimeDTO {
    private int hours;
    private int minutes;
    private int seconds;
    private int nano;
}
