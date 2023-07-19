package com.spring.task.car.mapper;

import com.spring.task.car.dto.OwnerCarDto;
import com.spring.task.car.model.Car;
import com.spring.task.car.model.Owner;
import com.spring.task.car.repository.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
public class OwnerCarDtoMapper {

//    private final OwnerRepository ownerRepository;

//    public OwnerCarDtoMapper(OwnerRepository ownerRepository){
//        this.ownerRepository = ownerRepository;
//    }

    public OwnerCarDto mapCarToOwnerCarDto(Car car){
        OwnerCarDto dto = new OwnerCarDto();
        dto.setId(car.getId());
        dto.setProducer(car.getProducer());
        dto.setModel(car.getModel());
        dto.setYearOfRelease(car.getYearOfRelease());
        dto.setPrice(car.getPrice());
        return dto;
    }

//    public Car mapOwnerCarDtoToCar(OwnerCarDto dto){
//        Car car = new Car();
//        car.setId(dto.getId());
//        car.setProducer(dto.getProducer());
//        car.setModel(dto.getModel());
//        car.setYearOfRelease(dto.getYearOfRelease());
//        car.setPrice(dto.getPrice());
//        Owner owner = ownerRepository.findById(dto.getId()).orElseThrow();
//        car.setOwner(owner);
//        return car;
//    }
}
