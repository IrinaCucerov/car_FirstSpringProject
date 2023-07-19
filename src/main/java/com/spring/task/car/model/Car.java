package com.spring.task.car.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "producer")
    private String producer;
    @Column(name = "model")
    private String model;
    @Column(name = "year_of_release")
    private String yearOfRelease;
    @Column(name = "price")
    private double price;
    @Column(name = "created_by")
    private String createdBy;
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
//    @JsonIgnore
    private Owner owner;

    @JsonProperty
    String ownerName(){
        return owner.getName();
    }
}
