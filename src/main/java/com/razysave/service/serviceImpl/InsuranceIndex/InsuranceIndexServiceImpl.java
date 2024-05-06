package com.razysave.service.serviceImpl.InsuranceIndex;

import com.razysave.controller.BuildingController;
import com.razysave.dto.InsuranceIndexDto;
import com.razysave.entity.insurance.InsuranceIndex;
import com.razysave.exception.InsuranceIndexNotFoundException;
import com.razysave.repository.device.DeviceRepository;
import com.razysave.repository.insurance.InsuranceIndexRepository;
import com.razysave.repository.property.BuildingRepository;
import com.razysave.repository.property.PropertyRepository;
import com.razysave.repository.property.UnitRepository;
import com.razysave.service.insurance.InsuranceIndexService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InsuranceIndexServiceImpl implements InsuranceIndexService {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private InsuranceIndexRepository insuranceIndexRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<InsuranceIndexDto> getInsuranceIndex() {
        logger.info("inside of getInsuranceIndex() method");
        List<InsuranceIndex> insuranceIndexDtos = insuranceIndexRepository.findAll();
        logger.info("End of getInsuranceIndex() method");
        return insuranceIndexDtos.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public InsuranceIndexDto getInsuranceIndexById(Integer id) {
        logger.info("inside of getInsuranceIndexById(Integer id)  method");
        Optional<InsuranceIndex> insuranceIndexOptional = insuranceIndexRepository.findById(id);
        if (insuranceIndexOptional.isPresent()) {
            logger.info("End of getInsuranceIndexById(Integer id)  method");
            return modelMapper.map(insuranceIndexOptional.get(), InsuranceIndexDto.class);
        } else {
            logger.info("End of getInsuranceIndexById(Integer id)  method with Exception");
            throw new InsuranceIndexNotFoundException("InsuranceIndex not found with id: " + id);
        }
    }

    public InsuranceIndex addInsuranceIndex(InsuranceIndex insuranceIndex) {
        logger.info("Enter of addInsuranceIndex(InsuranceIndex insuranceIndex) method");
        return insuranceIndexRepository.save(insuranceIndex);
    }

    public InsuranceIndex updateInsuranceIndexById(Integer id, InsuranceIndex updateInsuranceIndex) {
        logger.info("Enter of updateInsuranceIndexById(Integer id, InsuranceIndex updateInsuranceIndex) method");
        Optional<InsuranceIndex> existingInsuranceIndexOptional = insuranceIndexRepository.findById(id);
        if (existingInsuranceIndexOptional.isPresent()) {
            InsuranceIndex exisitingInsuranceIndex = existingInsuranceIndexOptional.get();
            if (updateInsuranceIndex.getAverageOccupancy() != null) {
                exisitingInsuranceIndex.setAverageOccupancy(updateInsuranceIndex.getAverageOccupancy());
            }
            if (updateInsuranceIndex.getEviction() != null) {
                exisitingInsuranceIndex.setEviction(updateInsuranceIndex.getEviction());
            }
            if (updateInsuranceIndex.getUnregisteredVehicle() != null) {
                exisitingInsuranceIndex.setUnregisteredVehicle(updateInsuranceIndex.getUnregisteredVehicle());
            }
            if (updateInsuranceIndex.getVacantAlert() != null) {
                exisitingInsuranceIndex.setVacantAlert(updateInsuranceIndex.getVacantAlert());
            }
            if (updateInsuranceIndex.getCurfewActivity() != null) {
                exisitingInsuranceIndex.setCurfewActivity(updateInsuranceIndex.getCurfewActivity());
            }
            logger.info("End of updateInsuranceIndexById(Integer id, InsuranceIndex updateInsuranceIndex) method");
            return insuranceIndexRepository.save(exisitingInsuranceIndex);
        } else {
            logger.info("End of updateInsuranceIndexById(Integer id, InsuranceIndex updateInsuranceIndex) method with Exception");
            throw new InsuranceIndexNotFoundException("InsuranceIndex not found with id: " + id);
        }
    }

    public void deleteInsuranceIndexById(Integer id) {
        logger.info("Enter of deleteInsuranceIndexById(Integer id)  method");
        Optional<InsuranceIndex> insuranceIndexOptional = insuranceIndexRepository.findById(id);
        if (insuranceIndexOptional.isPresent()) {
            logger.info("End of deleteInsuranceIndexById(Integer id) method deleted successfully");
            insuranceIndexRepository.deleteById(id);
        } else {
            throw new InsuranceIndexNotFoundException("InsuranceIndex not found with id: " + id);
        }
    }

    private InsuranceIndexDto mapToDto(InsuranceIndex insuranceIndex) {
        InsuranceIndexDto dto = modelMapper.map(insuranceIndex, InsuranceIndexDto.class);
        return dto;
    }
}
