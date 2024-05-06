package com.razysave.service.serviceImpl.property;

import com.razysave.controller.BuildingController;
import com.razysave.dto.BuildingListDto;
import com.razysave.entity.property.Building;
import com.razysave.entity.property.Property;
import com.razysave.entity.property.Unit;
import com.razysave.exception.BuildingNotFoundException;
import com.razysave.repository.device.DeviceRepository;
import com.razysave.repository.property.BuildingRepository;
import com.razysave.repository.property.PropertyRepository;
import com.razysave.repository.property.UnitRepository;
import com.razysave.service.property.BuildingService;
import com.razysave.service.property.UnitService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UnitService unitService;
    private ModelMapper modelMapper = new ModelMapper();

    public List<BuildingListDto> getBuildings() {
        logger.info("inside of getBuildings()  method");
        List<Building> buildings = buildingRepository.findAll();
        logger.info("End of getBuildings()  method");
        return buildings.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Building getBuildingById(Integer id) {
        logger.info("inside of getBuildingById(Integer id)  method");
        Optional<Building> buildingOptional = buildingRepository.findById(id);
        if (buildingOptional.isPresent()) {
            logger.info("End of getBuildingById(Integer id)  method");
            return buildingOptional.get();
        } else {
            logger.info("End of getBuildingById(Integer id)  method with Exception");
            throw new BuildingNotFoundException("Building not found with id: " + id);
        }
    }

    public Building addBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Building updateBuilding(Integer id, Building updatedBuilding) {
        logger.info("inside of updateBuilding(Integer id, Building updatedBuilding) method");
        Optional<Building> exisitingBuildingOptional = buildingRepository.findById(id);
        if (exisitingBuildingOptional.isPresent()) {
            Building exisitingBuilding = exisitingBuildingOptional.get();
            if (updatedBuilding.getName() != null) {
                exisitingBuilding.setName(updatedBuilding.getName());
            }
            logger.info("End of updateBuilding(Integer id, Building updatedBuilding) method");
            return buildingRepository.save(exisitingBuilding);
        } else {
            logger.info("End of updateBuilding(Integer id, Building updatedBuilding) method with Exception");
            throw new BuildingNotFoundException("Building not found with id: " + id);
        }
    }

    public void deleteBuildingById(Integer id) {
        logger.info("Enter deleteBuildingById(Integer id)  method");
        Optional<Building> buildingOptional = buildingRepository.findById(id);
        if (buildingOptional.isPresent()) {
            Building building = buildingOptional.get();
            List<Unit> units = building.getUnits();
            Integer propertyId = building.getPropertyId();
            Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
            if (optionalProperty.isPresent()) {
                Property property = optionalProperty.get();
                property.setUnitCount(property.getUnitCount() - building.getUnitCount());
                property.setBuildingCount(property.getBuildingCount() - 1);
                property.getBuilding().remove(building);
                propertyRepository.save(property);
            }
            for (Unit unit : units) {
                unitService.deleteUnitById(unit.getId());
            }
            buildingRepository.deleteById(id);
        } else {
            throw new BuildingNotFoundException("Building not found with id: " + id);
        }
    }

    private BuildingListDto mapToDto(Building building) {
        BuildingListDto dto = modelMapper.map(building, BuildingListDto.class);
        dto.setUnitCount(building.getUnitCount());
        dto.setDeviceCount(building.getDeviceCount());
        return dto;
    }
}
