package com.spring.task.car.servicePlusComtroller;

import com.spring.task.car.dto.OwnerCarDto;
import com.spring.task.car.mapper.OwnerCarDtoMapper;
import com.spring.task.car.mapper.OwnerDtoMapper;
import com.spring.task.car.model.Car;
import com.spring.task.car.model.Owner;
import com.spring.task.car.repository.FindCarRepository;
import com.spring.task.car.repository.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerDtoMapper ownerDtoMapper;
    private final OwnerCarDtoMapper ownerCarDtoMapper;

    public OwnerService(OwnerRepository ownerRepository,
                        OwnerDtoMapper ownerDtoMapper,
                        OwnerCarDtoMapper ownerCarDtoMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerDtoMapper = ownerDtoMapper;
        this.ownerCarDtoMapper = ownerCarDtoMapper;
    }
    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }
    public Optional<Owner> getById(Long id) {
        return ownerRepository.findById(id);
    }
    public  Owner createOwner(Owner owner){
        return ownerRepository.save(owner);
    }
    void deleteOwner(Long id){
        ownerRepository.deleteById(id);
    }

    List<OwnerCarDto> findCarsByOwnerId(Long ownerId){
        return ownerRepository.findById(ownerId)
                .map(Owner::getCars)
                .orElse(Collections.emptyList())
                .stream()
                .map(ownerCarDtoMapper::mapCarToOwnerCarDto)
                .toList();
    }

//    public List<Car> findCarsByOwnerId(Long ownerId){
//        return ownerRepository.findById(ownerId)
//                .map(Owner::getCars)
//                .orElse(Collections.emptyList());
////                .stream()
////                .toList();
//
//    }

    public Optional<Owner> updateOwner(Long id, Owner owner){
        if (!ownerRepository.existsById(id)){
            return Optional.empty();
        }
        Owner updateOwner = ownerRepository.save(owner);
        return Optional.of(owner);
    }

    void updatePatchOfOwner(Owner owner){
        ownerRepository.save(owner);
    }

}
