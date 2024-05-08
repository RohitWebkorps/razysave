package com.razysave.controller;

import com.razysave.dto.BuildingListDto;
import com.razysave.entity.property.Building;
import com.razysave.exception.BuildingNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.property.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/property/{propertyId}/list")
    public ResponseEntity<List<BuildingListDto>> getBuildings(@PathVariable Integer propertyId) {
        logger.info("Fetching Building list");
        try {
        List<BuildingListDto> buildings = buildingService.getBuildings(propertyId);
            logger.info("Fetched Building list successfully");
            return ResponseEntity.ok(buildings);
        } catch (BuildingNotFoundException e) {
            logger.info("Building list is empty");
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Building with id {}", id);
            Building building = buildingService.getBuildingById(id);
            logger.info("Building with id {} fetched successfully", id);
            return ResponseEntity.ok(building);
        } catch (BuildingNotFoundException e) {
            logger.error("An BuildingNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addBuilding(@RequestBody Building building) {
        buildingService.addBuilding(building);
       // studentRepository.save(student);
        return ResponseHandler.generateResponse("Added succesfully", HttpStatus.CREATED, building);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(@PathVariable Integer id, @RequestBody Building updatedBuilding) {
        try {
            logger.info("Fetching Building with id {}", id);
            Building building = buildingService.updateBuilding(id, updatedBuilding);
            logger.info("Building with id {} fetched successfully", id);
            return ResponseEntity.ok(building);
        } catch (BuildingNotFoundException e) {
            logger.error("An BuildingNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Integer id) {
        try {
            logger.info("Deleting Building with id {}", id);
            buildingService.deleteBuildingById(id);
            logger.info("Building with id {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } catch (BuildingNotFoundException e) {
            logger.error("An BuildingNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
