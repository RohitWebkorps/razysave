package com.razysave.service.serviceImpl.property;

import com.razysave.dto.property.PropertyDto;
import com.razysave.entity.property.Building;
import com.razysave.entity.property.Property;
import com.razysave.exception.PropertyNotFoundException;
import com.razysave.repository.property.PropertyRepository;
import com.razysave.service.property.BuildingService;
import com.razysave.service.property.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private BuildingService buildingService;
    private ModelMapper modelMapper = new ModelMapper();

    public List<PropertyDto> getProperties() {
        List<Property> properties = propertyRepository.findAll();
        if(properties.isEmpty())
            throw new PropertyNotFoundException("No Property Found");
        else
            return properties.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
    }

    public PropertyDto getPropertyById(Integer id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isPresent()) {
            PropertyDto propertyDto = modelMapper.map(propertyOptional.get(), PropertyDto.class);
            return propertyDto;
        } else {
            throw new PropertyNotFoundException("Property not found with id: " + id);
        }
    }


    public Property addProperty(Property property) {
       //property.setId(4);
       return propertyRepository.save(property);
    }

    public Property updateProperty(Integer id, Property updatedproperty) {

        Optional<Property> exisitingPropertyOptional = propertyRepository.findById(id);
        if (exisitingPropertyOptional.isPresent()) {
            Property exisitingProperty = exisitingPropertyOptional.get();
            if (updatedproperty.getName() != null) {
                exisitingProperty.setName(updatedproperty.getName());
            }
            if (updatedproperty.getBuildingCount() != null) {
                exisitingProperty.setBuildingCount(updatedproperty.getBuildingCount());
            }
            if (updatedproperty.getUnitCount() != null) {
                exisitingProperty.setUnitCount(updatedproperty.getUnitCount());
            }
            if (updatedproperty.getSize() != null) {
                exisitingProperty.setSize(updatedproperty.getSize());
            }
            if (updatedproperty.getOccupancy() != null) {
                exisitingProperty.setOccupancy(updatedproperty.getOccupancy());
            }
            if (updatedproperty.getOwner() != null) {
                exisitingProperty.setOwner(updatedproperty.getOwner());
            }
            if (updatedproperty.getPhone() != null) {
                exisitingProperty.setPhone(updatedproperty.getPhone());
            }
            if (updatedproperty.getEmail() != null) {
                exisitingProperty.setEmail(updatedproperty.getEmail());
            }
            if (updatedproperty.getBuilding() != null) {
                if (exisitingProperty.getBuilding() != null)
                    exisitingProperty.getBuilding().addAll(updatedproperty.getBuilding());
                else exisitingProperty.setBuilding(updatedproperty.getBuilding());
                exisitingProperty.setBuildingCount(exisitingProperty.getBuilding().size());
            }
            if (updatedproperty.getTenantCount() != null) {
                exisitingProperty.setTenantCount(updatedproperty.getTenantCount());
            }

            return propertyRepository.save(exisitingProperty);
        } else {
            throw new PropertyNotFoundException("Property not found with id : " + id);
        }
    }

    public void deletePropertyById(Integer id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            List<Building> buildings = property.getBuilding();
            if(buildings!=null) {
                for (Building building : buildings) {
                    buildingService.deleteBuildingById(building.getId());
                }
            }
            propertyRepository.deleteById(id);
        } else {
            throw new PropertyNotFoundException("Property Not found with id :" + id);
        }
    }
    private PropertyDto mapToDto(Property property) {
        PropertyDto dto = modelMapper.map(property, PropertyDto.class);
        return dto;
    }
}


