package com.razysave.dto.property;

import lombok.Data;

@Data
public class GraphWeekDto {
    private Integer id;
    private Integer propertyId;
    private Integer unitId;
    private Integer mon;
    private Integer tue;
    private Integer wed;
    private Integer thu;
    private Integer fri;
    private Integer sat;
    private Integer sun;
    public int getTotalCount() {
        return mon + tue + wed + thu + fri + sat + sun;
    }
}
