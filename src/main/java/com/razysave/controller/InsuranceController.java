package com.razysave.controller;

import com.razysave.dto.InsuranceIndexDto;
import com.razysave.entity.insurance.InsuranceIndex;
import com.razysave.exception.DeviceNotFoundException;
import com.razysave.exception.InsuranceIndexNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.insurance.InsuranceIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private InsuranceIndexService insuranceIndexService;

    @GetMapping
    public ResponseEntity<List<InsuranceIndexDto>> getInsuranceIndex() {
        try {
            logger.info("Fetching Insurance");
            List<InsuranceIndexDto> insuranceIndex = insuranceIndexService.getInsuranceIndex();
            logger.info("Fetched insurance index list successfully");
            return ResponseEntity.ok(insuranceIndex);
        } catch (InsuranceIndexNotFoundException e) {
            logger.error("InsuranceIndexNotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceIndexDto> getInsuranceIndexById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Device with {}",id);
            InsuranceIndexDto insuranceIndexDto = insuranceIndexService.getInsuranceIndexById(id);
            return ResponseEntity.ok(insuranceIndexDto);
        } catch (InsuranceIndexNotFoundException e) {
            logger.error("An InsuranceIndexNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addInsuranceIndex(@RequestBody InsuranceIndex insuranceIndex) {
        insuranceIndexService.addInsuranceIndex(insuranceIndex);
        return ResponseEntity.ok(insuranceIndex);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceIndex> updateInsuranceIndex(@PathVariable Integer id, @RequestBody InsuranceIndex insuranceIndex) {
        try {
            logger.info("Updating insuranceIndex with {}",id);
            InsuranceIndex insuranceIndexUpdated= insuranceIndexService.updateInsuranceIndexById(id, insuranceIndex);
            logger.info("Updated insuranceIndex with {}",id);
            return ResponseEntity.ok(insuranceIndexUpdated);
        } catch (DeviceNotFoundException e) {
            logger.error("An KPINotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInsuranceIndex(@PathVariable Integer id) {
        try {
            logger.info("Deleting insuranceIndex with {}",id);
            insuranceIndexService.deleteInsuranceIndexById(id);
            logger.info("Deleting insuranceIndex with {}",id);
            return ResponseHandler.generateResponse("deleted succesfully", HttpStatus.CREATED, id);
        } catch (InsuranceIndexNotFoundException e) {
            logger.error("An InsuranceIndexNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
