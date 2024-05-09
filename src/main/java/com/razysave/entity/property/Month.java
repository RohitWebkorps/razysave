package com.razysave.entity.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Month {
    private Integer week1;
    private Integer week2;
    private Integer week3;
    private Integer week4;
}
