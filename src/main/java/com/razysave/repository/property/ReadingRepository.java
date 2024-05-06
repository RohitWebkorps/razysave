package com.razysave.repository.property;

import com.razysave.entity.property.Reading;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingRepository extends MongoRepository<Reading, Integer> {
}
