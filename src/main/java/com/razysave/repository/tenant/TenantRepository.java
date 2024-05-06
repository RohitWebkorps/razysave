package com.razysave.repository.tenant;

import com.razysave.entity.tenant.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository extends MongoRepository<Tenant, Integer> {
    public Tenant findByName(String name);

    public Tenant deleteByName(String name);

    public Tenant findByUnitId(Integer id);
}
