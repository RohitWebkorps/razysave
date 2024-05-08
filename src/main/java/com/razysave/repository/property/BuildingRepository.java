package com.razysave.repository.property;

import com.razysave.entity.property.Building;
import com.razysave.entity.property.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BuildingRepository extends MongoRepository<Building, Integer> {
    public List<Building>  findByPropertyId(Integer propertyId);

}
