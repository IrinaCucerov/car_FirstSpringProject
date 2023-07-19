package com.spring.task.car.dto;

import lombok.Data;

@Data
public class OwnerDto {
    private Long id;
    private String name;
    private String lastname;
    private String telephone;
    private String pesel;
    private String email;

}
