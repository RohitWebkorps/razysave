package com.razysave.entity.devices;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "devices")
public class Device {
    @Id
    private Integer id;
    private Integer unitId;
    private String name;
    private Integer battery;
    private String connection;
    private Date installedDate;
    private String status;
    private String offlineTime;
    private String offlineSince;
    private Map<String, String> reading;
}
