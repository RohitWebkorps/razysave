package com.razysave.entity.insurance;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class InsuranceIndex {
    @Id
    private Integer id;
    private Integer propertyId;
    private String averageOccupancy;
    private String eviction;
    private Integer unregisteredVehicle;
    private Integer vacantAlert;
    private Integer curfewActivity;
}
