package com.spring.task.car.mapper;

import com.spring.task.car.model.Owner;
import com.spring.task.car.dto.OwnerDto;
import com.spring.task.car.request.CreateOwnerDto;
import org.springframework.stereotype.Component;

@Component
public class OwnerDtoMapper {

    public OwnerDto mapOwnerToOwnerDto(Owner owner){
        OwnerDto dto = new OwnerDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setLastname(owner.getLastname());
        dto.setTelephone(owner.getTelephone());
        dto.setPesel(owner.getPesel());
        dto.setEmail(owner.getEmail());
        return dto;
    }

    public Owner mapOwnerDtoToOwner(OwnerDto dto){
        Owner owner = new Owner();
        owner.setId(dto.getId());
        owner.setName(dto.getName());
        owner.setLastname(dto.getLastname());
        owner.setTelephone(dto.getTelephone());
        owner.setPesel(dto.getPesel());
        owner.setEmail(dto.getEmail());
        return owner;
    }

    public Owner mapCreateOwnerDtoToOwner(CreateOwnerDto createOwnerDto){
        Owner owner = new Owner();
        owner.setName(createOwnerDto.getName());
        owner.setLastname(createOwnerDto.getLastname());
        owner.setTelephone(createOwnerDto.getTelephone());
        owner.setPesel(createOwnerDto.getPesel());
        owner.setEmail(createOwnerDto.getEmail());
        return owner;
    }

}
