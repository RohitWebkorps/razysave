package com.razysave.controller;

import com.razysave.dto.device.ActiveDeviceDto;
import com.razysave.dto.device.DeviceListDto;
import com.razysave.dto.device.InstalledDevices;
import com.razysave.dto.device.OfflineDeviceDto;
import com.razysave.entity.devices.Device;
import com.razysave.exception.DeviceNotFoundException;
import com.razysave.response.ResponseHandler;
import com.razysave.service.devices.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/device")
public class DeviceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{name}/info/property/{propertyId}")
    public ResponseEntity<Object> getDevices(@PathVariable String name,@PathVariable Integer propertyId) {
        try {
            logger.info("Fetching Device list");
            List<DeviceListDto> devices = deviceService.getDevices(name,propertyId);
            logger.info("Fetched Device list successfully");
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDeviceById(@PathVariable Integer id) {
        try {
            logger.info("Fetching Device with {}",id);
            DeviceListDto deviceListDto = deviceService.getDeviceById(id);
            return ResponseEntity.ok(deviceListDto);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

  @PostMapping("/")
   public ResponseEntity<Object> addDevice(@RequestBody Device device) {
      deviceService.addDevice(device);
      return ResponseHandler.generateResponse("Added succesfully", HttpStatus.CREATED, device);
   }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDevice(@PathVariable Integer id, @RequestBody Device device) {
        try {
            logger.info("Updating Device with {}",id);
            deviceService.updateDevice(id, device);
            logger.info("Updated Device with {}",id);
            return ResponseHandler.generateResponse("updated succesfully", HttpStatus.CREATED, device);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDevice(@PathVariable Integer id) {
        try {
            logger.info("Deleting Device with {}",id);
            deviceService.deleteDeviceById(id);
            logger.info("Deleting Device with {}",id);
            return ResponseHandler.generateResponse("deleted succesfully", HttpStatus.CREATED, id);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred, {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/active-alert/property/{propertyId}")
    public ResponseEntity<Object> getActiveDevices(@PathVariable Integer propertyId) {
        try {
            logger.info("Fetching Device list");
            List<ActiveDeviceDto> devices = deviceService.getDevicesOnAlert(propertyId);
            logger.info("Fetched Device list successfully");
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/offline/property/{propertyId}")
    public ResponseEntity<Object> getOfflineDevice(@PathVariable Integer propertyId) {
        try {
            logger.info("Fetching Offline Device list");
            List<OfflineDeviceDto> devices = deviceService.getOfflineDevices(propertyId);
            logger.info("Fetched Device list successfully");
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/installed-devices/property/{propertyId}")
    public ResponseEntity<Object> getInstalledDevices(@PathVariable Integer propertyId) {
        try {
            logger.info("Fetching Installed Device list");
            InstalledDevices devices = deviceService.getInstalledDevices(propertyId);
            logger.info("Fetched Device list successfully");
            return ResponseEntity.ok(devices);
        } catch (DeviceNotFoundException e) {
            logger.error("An DeviceNotFoundException exception occurred {},", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
