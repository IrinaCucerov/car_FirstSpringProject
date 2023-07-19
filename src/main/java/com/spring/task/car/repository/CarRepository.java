package com.spring.task.car.repository;

import com.spring.task.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>  {

}
