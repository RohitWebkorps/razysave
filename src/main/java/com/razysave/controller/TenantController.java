package com.razysave.controller;

import com.razysave.dto.tenant.TenantDto;
import com.razysave.entity.tenant.Tenant;
import com.razysave.exception.BuildingNotFoundException;
import com.razysave.exception.TenantNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.property.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    @Autowired
    private TenantService tenantService;
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<Object> getTenants(@PathVariable Integer unitId) {
        logger.info("Fetching Tenant list");
        List<TenantDto> tenants = tenantService.getTenants(unitId);
        if (tenants.isEmpty()) {
            logger.info("Tenant list is empty");
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            logger.info("Fetched Tenant list successfully");
            return ResponseEntity.ok(tenants);
        }
    } @GetMapping("/property/{propertyId}")
    public ResponseEntity<Object> getTenantsByProperty(@PathVariable Integer propertyId) {
        logger.info("Fetching Tenant list");
        List<TenantDto> tenants = tenantService.getTenantsByPropertyId(propertyId);
        if (tenants.isEmpty()) {
            logger.info("Tenant list is empty");
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            logger.info("Fetched Tenant list successfully");
            return ResponseEntity.ok(tenants);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getTenantByName(@PathVariable Integer id) {
        try {
            logger.info("Fetching Tenant with id {}", id);
            Tenant tenant = tenantService.getTenantById(id);
            return ResponseEntity.ok(tenant);
        } catch (BuildingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
        return ResponseHandler.generateResponse("Added succesfully", HttpStatus.CREATED, tenant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTenant(@PathVariable Integer id, @RequestBody Tenant updatedTenant) {
        try {
            logger.info("Updating Tenant with id {}", id);
            Tenant tenant = tenantService.updateTenant(id, updatedTenant);
            logger.info("Updated Tenant with id {}", id);
            return ResponseEntity.ok(tenant);
        } catch (TenantNotFoundException e) {
            logger.error("An TenantNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTenant(@PathVariable Integer id) {
        try {
            logger.info("Deleting Tenant with id {}", id);
            tenantService.deleteTenantById(id);
            logger.info("Deleted Tenant with id {}", id);
            return ResponseEntity.noContent().build();
        } catch (BuildingNotFoundException e) {
            logger.error("An TenantNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
