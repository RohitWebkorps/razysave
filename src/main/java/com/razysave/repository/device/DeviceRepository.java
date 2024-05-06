package com.razysave.repository.device;

import com.razysave.entity.devices.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends MongoRepository<Device, Integer> {
public List<Device> findByName(String name);
    public List<Device> findByStatus(String status);
}
