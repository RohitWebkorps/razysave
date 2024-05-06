package com.razysave.entity.tenant;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "tenants")
public class Tenant {
    @Id
    private Integer id;
    private Integer unitId;
    private String name;
    private String role;
    private String joined;
    private String phone;
    private String email;
}
