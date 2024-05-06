package com.razysave.service.property;

import com.razysave.entity.property.Property;
import com.razysave.entity.property.Reading;

import java.util.List;

public interface ReadingService {
    public List<Reading> getReadings();
    public Reading getReadingById(Integer id);
    public Reading addReading(Reading reading);

    public Reading updateReading(String id, Reading reading);

    public void deleteReadingById(Integer id);
}
