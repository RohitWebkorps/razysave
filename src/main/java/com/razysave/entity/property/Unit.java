package com.razysave.entity.property;

import com.razysave.entity.devices.Device;
import com.razysave.entity.tenant.Tenant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "units")
public class Unit {
    @Id
    private Integer id;
    private String name;
    private Integer buildingId;
    private List<Device> deviceList;
    private LocalDate installedDate;
    private Tenant tenant;
    private boolean occupied;
}
