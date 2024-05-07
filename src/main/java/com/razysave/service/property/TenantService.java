package com.razysave.service.property;

import com.razysave.dto.tenant.TenantDto;
import com.razysave.entity.tenant.Tenant;

import java.util.List;

public interface TenantService {
    public List<TenantDto> getTenants();
    public Tenant getTenantById(Integer id);
    public Tenant addTenant(Tenant tenant);

    public Tenant updateTenant(Integer id, Tenant tenant);

    public void deleteTenantById(Integer id);
}
