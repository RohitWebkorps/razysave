package com.razysave.dto.property;

import lombok.Data;

@Data
public class GraphMonthDto {
    private Integer id;
    private Integer propertyId;
    private Integer unitId;
    private Integer week1;
    private Integer week2;
    private Integer week3;
    private Integer week4;
}
