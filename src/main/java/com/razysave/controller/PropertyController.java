package com.razysave.controller;

import com.razysave.dto.property.PropertyDto;
import com.razysave.entity.property.Property;
import com.razysave.exception.BuildingNotFoundException;
import com.razysave.exception.PropertyNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.property.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {
    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getProperties() {
        logger.info("Fetching Property list");
        try {
        List<PropertyDto> properties = propertyService.getProperties();
            return ResponseEntity.ok(properties);
        } catch(PropertyNotFoundException e) {
            logger.info("Fetched Property list successfully");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Property with id {}", id);
            Property property = propertyService.getPropertyById(id);
            logger.info("Property with id {} fetched successfully", id);
            return ResponseEntity.ok(property);
        } catch (PropertyNotFoundException e) {
            logger.error("An PropertyNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addProperties(@RequestBody Property property) {
        propertyService.addProperty(property);
        return ResponseHandler.generateResponse("Added successfully", HttpStatus.CREATED, property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Integer id, @RequestBody Property partialProperty) {
        try {
            logger.info("Fetching Property with id {}", id);
            Property property = propertyService.updateProperty(id, partialProperty);
            logger.info("Property with id {} fetched successfully", id);
            return ResponseEntity.ok(property);
        } catch (BuildingNotFoundException e) {
            logger.error("An PropertyNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProperty(@PathVariable Integer id) {
        try {
            logger.info("Deleting Property with id {}", id);
            propertyService.deletePropertyById(id);
            logger.info("Property with id {} fetched successfully", id);
            return ResponseEntity.noContent().build();
        } catch (BuildingNotFoundException e) {
            logger.error("An PropertyNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}