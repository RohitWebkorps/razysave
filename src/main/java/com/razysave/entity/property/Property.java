package com.razysave.entity.property;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "property")
public class Property {
    @Id
    private Integer id;
    private String name;
    private Integer buildingCount;
    private Integer unitCount;
    private String size;
    private String occupancy;
    private String Owner;
    private String phone;
    private String email;
    private Integer tenantCount;
    private List<Building> building;
}
