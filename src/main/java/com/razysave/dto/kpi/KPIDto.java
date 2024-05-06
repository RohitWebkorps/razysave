package com.razysave.dto.kpi;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
@Getter
@Setter
public class KPIDto {
    private Integer id;
    private Integer propertyId;
    private HashMap<String, Integer> gallons;
    private HashMap<String, String> expenses;
    private HashMap<String, String> revenue;
    private HashMap<String, Integer> pAndL;
}
