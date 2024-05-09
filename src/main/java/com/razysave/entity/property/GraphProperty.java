package com.razysave.entity.property;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class GraphProperty {
    private Integer id;
    private Integer propertyId;
    private Week estWeek;
    private Month estMonth;
    private Year estYear;
    private Week curWeek;
    private Month curMonth;
    private Year curYear;
}
