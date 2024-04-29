package org.cloud.car.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CarCreateDTO {

    @NotNull
    private CarTimeDTO startTime;
    @NotNull
    private CarTimeDTO endTime;
    @NonNull
    private String licencePlate;
}
