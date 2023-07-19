package com.spring.task.car.servicePlusComtroller;

import com.spring.task.car.mapper.CarDtoMapper;
import com.spring.task.car.model.Car;
import com.spring.task.car.repository.CarRepository;
import com.spring.task.car.repository.FindCarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;
//    private final FindCarRepository findCarRepository;

    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
    }

    public Optional<Car> getById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Optional<Car> updateCar(Long id, Car car){
        if (!carRepository.existsById(id)){
            return Optional.empty();
        }
        Car updatedCar = carRepository.save(car);
        return Optional.of(car);
    }

    void  updatePartOfCar (Car car){
        carRepository.save(car);
    }


    void deleteCar(Long id){
        carRepository.deleteById(id);
    }

//    public List<Car> findCarByOwnerId(){
//        return findCarRepository.findAll();
//    }
}
