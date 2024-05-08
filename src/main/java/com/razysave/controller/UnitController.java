package com.razysave.controller;

import com.razysave.dto.unit.UnitInfoDto;
import com.razysave.dto.unit.UnitListDto;
import com.razysave.entity.property.Unit;
import com.razysave.exception.UnitNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.property.UnitService;
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
public class UnitController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private UnitService unitService;

    @GetMapping("{buildingId}/unit/list")
    public ResponseEntity<Object> getUnits(@PathVariable Integer buildingId) {
        logger.info("Fetching Unit list");
        List<UnitListDto> units = unitService.getUnits(buildingId);
        if (units.isEmpty()) {
            logger.info("Unit list is empty");
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            logger.info("Fetched Unit list successfully");
            return ResponseEntity.ok(units);
        }
    }
    @GetMapping("/list/property/{propertyId}")
    public ResponseEntity<Object> getUnitByPropertyId(@PathVariable Integer propertyId) {
        logger.info("Fetching Unit list");
        List<UnitListDto> units = unitService.getUnitsByProperty(propertyId);
        if (units.isEmpty()) {
            logger.info("Unit list is empty");
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            logger.info("Fetched Unit list successfully");
            return ResponseEntity.ok(units);
        }
    }


    @GetMapping("/info/{id}")
    public ResponseEntity<Object> getUnitById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Unit with id {}", id);
            UnitInfoDto unit = unitService.getUnitById(id);
            logger.info("Fetched Unit with id {}", id);
            return ResponseEntity.ok(unit);
        } catch (UnitNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUnits(@RequestBody Unit unit) {
        unitService.addUnit(unit);
        return ResponseHandler.generateResponse("Added succesfully", HttpStatus.CREATED, unit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUnit(@PathVariable Integer id, @RequestBody Unit updatedUnit) {
        try {
            logger.info("Updating Unit with id {}", id);
            Unit unit = unitService.updateUnit(id, updatedUnit);
            logger.info("Updated Unit with id {}", id);
            return ResponseEntity.ok(unit);
        } catch (UnitNotFoundException e) {
            logger.error("An UnitNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUnit(@PathVariable Integer id) {
        try {
            logger.info("Deleting Unit with id {}", id);
            unitService.deleteUnitById(id);
            logger.info("Deleted Unit with id {}", id);
            return ResponseEntity.noContent().build();
        } catch (UnitNotFoundException e) {
            logger.error("An UnitNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

