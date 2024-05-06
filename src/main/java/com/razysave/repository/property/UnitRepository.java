package com.razysave.repository.property;

import com.razysave.entity.property.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends MongoRepository<Unit, Integer> {
}
