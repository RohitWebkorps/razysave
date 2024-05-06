package com.razysave.controller;

import com.razysave.dto.kpi.KPIDto;
import com.razysave.entity.kpi.KPI;
import com.razysave.exception.DeviceNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.kpi.KPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kpi")
public class KPIController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private KPIService kpiService;

    @GetMapping
    public ResponseEntity<KPIDto> getKPI() {
        try {
            logger.info("Fetching KPI list");
            List<KPIDto> kpis = kpiService.getKPI();
            logger.info("Fetched KPI list successfully");
            return ResponseEntity.ok(null);
        } catch (DeviceNotFoundException e) {
            logger.error("An KPINotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<KPIDto> getKPIById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Device with {}",id);
            KPIDto kpiDto = kpiService.getKPIById(id);
            return ResponseEntity.ok(kpiDto);
        } catch (DeviceNotFoundException e) {
            logger.error("An KPINotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addKPI(@RequestBody KPI kpi) {
        kpiService.addKPI(kpi);
        return ResponseEntity.ok(kpi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKPI(@PathVariable Integer id, @RequestBody KPI kpi) {
        try {
            logger.info("Updating KPI with {}",id);
           KPI kpiUpdated= kpiService.updateKPIById(id, kpi);
            logger.info("Updated KPI with {}",id);
            return ResponseEntity.ok(kpiUpdated);
        } catch (DeviceNotFoundException e) {
            logger.error("An KPINotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKPI(@PathVariable Integer id) {
        try {
            logger.info("Deleting KPI with {}",id);
            kpiService.deleteKPIById(id);
            logger.info("Deleting KPI with {}",id);
            return ResponseHandler.generateResponse("deleted succesfully", HttpStatus.CREATED, id);
        } catch (DeviceNotFoundException e) {
            logger.error("An KPINotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
