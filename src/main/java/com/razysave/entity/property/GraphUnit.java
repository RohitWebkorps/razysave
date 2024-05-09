package com.razysave.entity.property;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class GraphUnit {
    @Id
    private Integer id;
    private Integer unitId;
    private Integer propertyId;
    private Week estWeek;
    private Month estMonth;
    private Year estYear;
    private Week curWeek;
    private Month curMonth;
    private Year curYear;
}
