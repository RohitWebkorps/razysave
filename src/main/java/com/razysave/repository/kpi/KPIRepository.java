package com.razysave.repository.kpi;

import com.razysave.entity.kpi.KPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPIRepository extends MongoRepository<KPI, Integer> {
}
