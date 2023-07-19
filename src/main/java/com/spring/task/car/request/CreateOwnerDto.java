package com.spring.task.car.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOwnerDto {
    private String name;
    private String lastname;
    private String telephone;
    private String pesel;
    private String email;
}
