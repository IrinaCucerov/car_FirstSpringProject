package com.spring.task.car.dto;

import lombok.Data;

@Data
public class CarDto {
    private long id;
    private String producer;
    private String model;
    private String yearOfRelease;
    private double price;
    private long ownerId;
    private String ownerName;
    }
