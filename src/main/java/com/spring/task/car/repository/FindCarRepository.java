package com.spring.task.car.repository;

import com.spring.task.car.dto.OwnerCarDto;
import com.spring.task.car.model.Car;
import com.spring.task.car.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FindCarRepository extends JpaRepository<Owner, Long>{
//        List<Car> findCarsByOwnerId();
}
