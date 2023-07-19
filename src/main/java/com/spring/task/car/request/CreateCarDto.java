package com.spring.task.car.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDto {
    private String producer;
    private String model;
    private String yearOfRelease;
    private double price;
    private long ownerId;
    private String ownerName;

}
