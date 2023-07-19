package com.spring.task.car.servicePlusComtroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.spring.task.car.dto.OwnerCarDto;
import com.spring.task.car.dto.OwnerDto;
import com.spring.task.car.mapper.OwnerCarDtoMapper;
import com.spring.task.car.mapper.OwnerDtoMapper;
import com.spring.task.car.model.Car;
import com.spring.task.car.model.Owner;
import com.spring.task.car.request.CreateOwnerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerDtoMapper ownerDtoMapper;

    private final ObjectMapper objectMapper;
    private final OwnerCarDtoMapper ownerCarDtoMapper;

    @GetMapping("/Owners")
    public ResponseEntity<List<OwnerDto>> findAll(){
        return ResponseEntity.ok(ownerService.findAll()
                .stream()
                .map(ownerDtoMapper::mapOwnerToOwnerDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/Owner/{id}")
    ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id){
        Optional<Owner> owner = (ownerService.getById(id));
        if (owner.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerDtoMapper.mapOwnerToOwnerDto(owner.get()));
    }

//    @GetMapping("/{ownerId}/Cars")
//    public ResponseEntity<List<OwnerCarDto>> findCarsByOwnerId(@PathVariable Long id){
//        List<Car> car = (ownerService.findCarsByOwnerId(id));
//        if (ownerService.findCarsByOwnerId(id).isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(ownerCarDtoMapper.mapCarToOwnerCarDto(car.get()));
//    }
    @GetMapping("/{ownerId}/Cars")
    ResponseEntity<List<OwnerCarDto>> findOwnersCar(@PathVariable Long ownerId){
        if (ownerService.getById(ownerId).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerService.findCarsByOwnerId(ownerId));
    }

    @PostMapping("/Owner-create")
    public ResponseEntity<OwnerDto> createNewOwner(@RequestBody CreateOwnerDto createOwnerDto){
        Owner owner = ownerDtoMapper.mapCreateOwnerDtoToOwner(createOwnerDto);
        Owner newOwner = (ownerService.createOwner(owner));

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newOwner.getId())
                .toUri()).body(ownerDtoMapper.mapOwnerToOwnerDto(newOwner));
    }

    @DeleteMapping("/Owner-delete/{id}")
    ResponseEntity<?> deleteOwner(@PathVariable Long id){
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("Owner-update/{id}")
    ResponseEntity<?> updateOwnerById(@PathVariable Long id, @RequestBody OwnerDto ownerDto){
        Owner owner = ownerDtoMapper.mapOwnerDtoToOwner(ownerDto);
        return ownerService.updateOwner(id, owner)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/Owner-updatePart/{id}")
    ResponseEntity<?> updatePartOwnerById(@PathVariable Long id, @RequestBody JsonMergePatch patch){
        try {
            Owner owner = ownerService.getById(id).orElseThrow();
            Owner ownerPatch = applyPatch(owner, patch);
            ownerService.updatePatchOfOwner(ownerPatch);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.internalServerError().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private Owner applyPatch(Owner owner, JsonMergePatch patch) throws JsonEOFException, JsonProcessingException, JsonPatchException {
        JsonNode ownerNode = objectMapper.valueToTree(owner);
        JsonNode patchedNode = patch.apply(ownerNode);
        return objectMapper.treeToValue(patchedNode, Owner.class);
    }


}
