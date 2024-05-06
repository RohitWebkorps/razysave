package com.razysave.service.property;

import com.razysave.entity.property.Property;
import java.util.List;

public interface PropertyService {
    public List<Property> getProperties();
    public Property getPropertyById(Integer id);
    public Property addProperty(Property property);

    public Property updateProperty(Integer id, Property property);

    public void deletePropertyById(Integer id);
}
