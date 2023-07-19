package com.spring.task.car.model;

import com.spring.task.car.model.Car;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Owner")
public class Owner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Car> cars = new ArrayList<>();
}
