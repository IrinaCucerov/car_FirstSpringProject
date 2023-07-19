package com.spring.task.car.dto;

import lombok.Data;

@Data
public class OwnerCarDto {
    private long id;
    private String producer;
    private String model;
    private String yearOfRelease;
    private double price;
}
