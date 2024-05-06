package com.razysave.repository.property;

import com.razysave.entity.property.Building;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuildingRepository extends MongoRepository<Building, Integer> {
}
