package com.razysave.service.serviceImpl.property;

import com.razysave.dto.tenant.TenantDto;
import com.razysave.entity.property.Building;
import com.razysave.entity.property.Property;
import com.razysave.entity.property.Unit;
import com.razysave.entity.tenant.Tenant;
import com.razysave.exception.TenantNotFoundException;
import com.razysave.repository.property.BuildingRepository;
import com.razysave.repository.property.PropertyRepository;
import com.razysave.repository.property.UnitRepository;
import com.razysave.repository.tenant.TenantRepository;
import com.razysave.service.property.TenantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<TenantDto> getTenants(Integer unitId) {
        List<Tenant> tenants = tenantRepository.findByUnitId(unitId);
        return tenants.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenantDto> getTenantsByPropertyId(Integer propertyId) {
        List<Tenant> tenants = tenantRepository.findByPropertyId(propertyId);
        return tenants.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Tenant getTenantById(Integer id) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(id);
        if (tenantOptional.isPresent())
            return tenantOptional.get();
        else
            throw new TenantNotFoundException("Tenant not found with id:" + id);


    }

    public Tenant addTenant(Tenant tenant) {
        Optional<Unit> unitOptional = unitRepository.findById(tenant.getUnitId());
        Unit unit = unitOptional.get();
        unit.setTenant(tenant);
        unitRepository.save(unit);
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Integer id, Tenant updatedtenant) {

        Optional<Tenant> exisitingTenantOptinal = tenantRepository.findById(id);
        if (exisitingTenantOptinal.isPresent()) {
            Tenant exisitingTenant = exisitingTenantOptinal.get();

            if (updatedtenant.getName() != null) {
                exisitingTenant.setName(updatedtenant.getName());
            }
            if (updatedtenant.getEmail() != null) {
                exisitingTenant.setEmail(updatedtenant.getEmail());
            }
            if (updatedtenant.getRole() != null) {
                exisitingTenant.setRole(updatedtenant.getRole());
            }
            if (updatedtenant.getJoined() != null) {
                exisitingTenant.setJoined(updatedtenant.getJoined());
            }
            if (updatedtenant.getPhone() != null) {
                exisitingTenant.setPhone(updatedtenant.getPhone());
            }

            return tenantRepository.save(exisitingTenant);
        } else {
            throw new RuntimeException("Tenant not found with id : " + updatedtenant.getId());
        }
    }

    public void deleteTenantById(Integer id) {
        Optional<Tenant> tenantOptinal = tenantRepository.findById(id);
        if (tenantOptinal.isPresent()) {
            Tenant tenant = tenantOptinal.get();
            Integer unitId = tenant.getUnitId();
            Optional<Unit> unitOptional = unitRepository.findById(unitId);
            if (unitOptional.isPresent()) {
                Unit unit = unitOptional.get();
                unit.setTenant(null);
                unit.setOccupied(false);
                Integer buildingId = unit.getBuildingId();
                Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
                if (buildingOptional.isPresent()) {
                    Building building = buildingOptional.get();
                    Integer propertyId = building.getPropertyId();
                    Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
                    if (propertyOptional.isPresent()) {
                        Property property = propertyOptional.get();
                        property.setTenantCount(property.getTenantCount() - 1);
                        propertyRepository.save(property);
                    }
                }
                unitRepository.save(unit);
            }
            tenantRepository.deleteById(id);
        }
        else{
            throw new TenantNotFoundException("Tenant not found with id : " +id);
        }
    }

    private TenantDto mapToDto(Tenant tenant) {
        TenantDto dto = modelMapper.map(tenant, TenantDto.class);
        Optional<Unit> unitOptional = unitRepository.findById(dto.getUnitId());
        if (unitOptional.isPresent()) {
            Unit unit = unitOptional.get();
            com.razysave.dto.tenant.Property tenantProperty = new com.razysave.dto.tenant.Property();
            tenantProperty.setUnitName(unit.getName());
            if (unit.getBuildingId() != null) {
                Optional<Building> buildingOptional = buildingRepository.findById(unit.getBuildingId());
                if (buildingOptional.isPresent()) {
                    Building building = buildingOptional.get();
                    Optional<Property> propertyOptional = propertyRepository.findById(building.getPropertyId());
                    if (propertyOptional.isPresent()) {
                        Property property = propertyOptional.get();
                        tenantProperty.setPropertyName(property.getName());
                        dto.setProperty(tenantProperty);
                    }
                }
            }
        }
        return dto;
    }
}
