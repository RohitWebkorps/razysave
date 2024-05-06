package com.razysave.repository.insurance;

import com.razysave.entity.insurance.InsuranceIndex;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceIndexRepository extends MongoRepository<InsuranceIndex, Integer> {
}
