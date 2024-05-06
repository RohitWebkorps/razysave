package com.razysave.service.serviceImpl.property;

import com.razysave.dto.device.DeviceListDto;
import com.razysave.dto.unit.UnitInfoDto;
import com.razysave.dto.unit.UnitListDto;
import com.razysave.entity.devices.Device;
import com.razysave.entity.property.Unit;
import com.razysave.entity.tenant.Tenant;
import com.razysave.exception.UnitNotFoundException;
import com.razysave.repository.device.DeviceRepository;
import com.razysave.repository.property.UnitRepository;
import com.razysave.repository.tenant.TenantRepository;
import com.razysave.service.property.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<UnitListDto> getUnits() {
        List<Unit> units = unitRepository.findAll();
        return units.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UnitInfoDto getUnitById(Integer id) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        if (unitOptional.isPresent()) {
            Unit unit = unitOptional.get();
            Tenant tenant = unit.getTenant();

            UnitInfoDto unitInfoDto = modelMapper.map(unit, UnitInfoDto.class);
            if (tenant != null) {
                unitInfoDto.setTenantName(tenant.getName());
                unitInfoDto.setTenantEmail(tenant.getEmail());
                unitInfoDto.setTenantPhone(tenant.getPhone());
                unitInfoDto.setStatus("Occupied");
            }
            unitInfoDto.setDeviceCount(unitInfoDto.countDevicesByName());
            return unitInfoDto;
        } else
            throw new RuntimeException("Unit not found with Id : " + id);
    }

    public Unit addUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public Unit updateUnit(Integer id, Unit updatedUnit) {

        Optional<Unit> exisitingUnit = unitRepository.findById(id);

        if (exisitingUnit.isPresent()) {
            Unit unit = exisitingUnit.get();
            if (updatedUnit.getName() != null) {
                unit.setName(updatedUnit.getName());
            }
            if (updatedUnit.getTenant() != null) {
                unit.setTenant(updatedUnit.getTenant());
                unit.setOccupied(true);
            }
            return unitRepository.save(unit);
        } else {
            throw new RuntimeException("Unit not found with Id : " + updatedUnit.getId());
        }
    }

    public void deleteUnitById(Integer id) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        if (unitOptional.isPresent()) {
            Unit unit = unitOptional.get();
            List<Device> devicesToUpdate = new ArrayList<>();
            Tenant tenant = unit.getTenant();
            if (tenant != null) {
                tenant.setUnitId(null);
            }
            List<Device> devices = unit.getDeviceList();
            for (Device device : devices) {
                device.setUnitId(null);
                devicesToUpdate.add(device);
            }
            tenantRepository.save(tenant);
            deviceRepository.saveAll(devicesToUpdate);
            unitRepository.deleteById(id);
        } else {
            throw new UnitNotFoundException("Property Not found with id :" + id);
        }
    }

    private UnitListDto mapToDto(Unit unit) {
        UnitListDto dto = modelMapper.map(unit, UnitListDto.class);
        List<DeviceListDto> devices = dto.getDeviceList();
        Tenant tenant = unit.getTenant();
        if (tenant != null) {
            dto.setTenantName(tenant.getName());
        }
        return dto;
    }

}