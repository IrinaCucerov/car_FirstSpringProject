package com.spring.task.car.servicePlusComtroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.spring.task.car.dto.CarDto;
import com.spring.task.car.mapper.CarDtoMapper;
import com.spring.task.car.model.Car;
import com.spring.task.car.request.CreateCarDto;
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
public class CarController {
    private final CarService carService;
    private final CarDtoMapper mapper;
    private final ObjectMapper objectMapper;

    @GetMapping("/Cars")
    public ResponseEntity<List<CarDto>> findAll(){
       return ResponseEntity.ok(carService.findAll()
               .stream()
               .map(mapper::mapCarToCarDto)
               .collect(Collectors.toList()));
    }

    @GetMapping("/Cars/{id}")
    ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        Optional<Car> car = (carService.getById(id));
        if(car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.mapCarToCarDto(car.get()));
    }

    @PostMapping("/Car-create")
    public ResponseEntity<CarDto> createNewCar(@RequestBody CreateCarDto createCarDto){
        Car car = mapper.mapCreateCarDtoToCar(createCarDto);
        Car newCar = (carService.createCar(car));

        return ResponseEntity.created(ServletUriComponentsBuilder
               .fromCurrentRequestUri()
               .path("/{id}")
               .buildAndExpand(newCar.getId())
               .toUri()).body(mapper.mapCarToCarDto(newCar));

    }

    @DeleteMapping("/Car-delete/{id}")
    ResponseEntity<?> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Car-update/{id}")
    ResponseEntity<?> updateCarById(@PathVariable Long id, @RequestBody CarDto carDto){
        Car car = mapper.mapCarDtoToCar(carDto);
//        Car updateCar = carService.updateCar(id, car);
        return carService.updateCar(id, car)
                .map(c -> ResponseEntity.noContent().build())
                        .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/Car-updatePart/{id}")
    ResponseEntity<?> updatePartCarById(@PathVariable Long id, @RequestBody JsonMergePatch patch){
        try {
            Car car = carService.getById(id).orElseThrow();
            Car carPatch = applyPatch(car, patch);
            carService.updatePartOfCar(carPatch);
        } catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.internalServerError().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private Car applyPatch(Car car, JsonMergePatch patch) throws JsonEOFException, JsonProcessingException, JsonPatchException {
        JsonNode carNode = objectMapper.valueToTree(car);
        JsonNode patchedNode = patch.apply(carNode);
        return objectMapper.treeToValue(patchedNode, Car.class);
    }

//    @GetMapping("/Cars/{ownerId}")
//    public ResponseEntity<List<CarDto>> findCarsByOwnerId(){
//        return ResponseEntity.ok(carService.findCarByOwnerId()
//                .stream()
//                .map(mapper::mapCarToCarDto)
//                .collect(Collectors.toList()));
//    }

}
