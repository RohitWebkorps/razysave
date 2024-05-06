package com.razysave.service.serviceImpl.property;

import com.razysave.entity.property.Building;
import com.razysave.entity.property.Property;
import com.razysave.entity.property.Unit;
import com.razysave.entity.tenant.Tenant;
import com.razysave.repository.property.BuildingRepository;
import com.razysave.repository.property.PropertyRepository;
import com.razysave.repository.property.UnitRepository;
import com.razysave.repository.tenant.TenantRepository;
import com.razysave.service.property.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Tenant> getTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants;
    }

    public Tenant getTenantByName(String name) {
        Tenant tenant = tenantRepository.findByName(name);
        return tenant;
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
            throw new RuntimeException("Tenant not found with id : " +id);
        }
    }
}
