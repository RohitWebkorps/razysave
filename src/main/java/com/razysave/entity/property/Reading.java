package com.razysave.entity.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "reading")
public class Reading {
    private String temprature;
    private String humidity;
}
