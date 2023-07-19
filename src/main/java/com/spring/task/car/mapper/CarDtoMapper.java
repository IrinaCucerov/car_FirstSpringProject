package com.spring.task.car.mapper;

import com.spring.task.car.model.Car;
import com.spring.task.car.dto.CarDto;
import com.spring.task.car.model.Owner;
import com.spring.task.car.repository.OwnerRepository;
import com.spring.task.car.request.CreateCarDto;
import org.springframework.stereotype.Component;

@Component
public class CarDtoMapper {

    private final OwnerRepository ownerRepository;

    public CarDtoMapper(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    public CarDto mapCarToCarDto(Car car){
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setProducer(car.getProducer());
        dto.setModel(car.getModel());
        dto.setYearOfRelease(car.getYearOfRelease());
        dto.setPrice(car.getPrice());
        dto.setOwnerId(car.getOwner().getId());
        dto.setOwnerName(car.getOwner().getName());
        return dto;
    }
    public Car mapCarDtoToCar(CarDto dto){
        Car car = new Car();
        car.setId(dto.getId());
        car.setProducer(dto.getProducer());
        car.setModel(dto.getModel());
        car.setYearOfRelease(dto.getYearOfRelease());
        car.setPrice(dto.getPrice());
        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow();
        car.setOwner(owner);
        return car;
    }
    public Car mapCreateCarDtoToCar(CreateCarDto createCarDto){
        Car car = new Car();
        car.setProducer(createCarDto.getProducer());
        car.setModel(createCarDto.getModel());
        car.setYearOfRelease(createCarDto.getYearOfRelease());
        car.setPrice(createCarDto.getPrice());
        Owner owner = ownerRepository.findById(createCarDto.getOwnerId()).orElseThrow();
        car.setOwner(owner);
        return car;
    }
//    public Car mapCarDtoToPatch(JsonMergePatch patch){
//        Car car = new Car();
//        car.setId(patch.getClass());
//        car.setProducer(dto.getProducer());
//        car.setModel(dto.getModel());
//        car.setYearOfRelease(dto.getYearOfRelease());
//        car.setPrice(dto.getPrice());
//        return car;
//    }

}
