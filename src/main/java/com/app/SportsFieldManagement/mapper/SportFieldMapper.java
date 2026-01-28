package com.app.SportsFieldManagement.mapper;

import com.app.SportsFieldManagement.dto.response.SportFieldResponse;
import com.app.SportsFieldManagement.model.SportField;
import org.springframework.stereotype.Component;

@Component
public class SportFieldMapper {
    public SportFieldResponse toResponse(SportField sportField){
        return new SportFieldResponse(sportField.getId(),sportField.getName(),
                sportField.getSportType(),
                sportField.getPrice(),
                sportField.isIndoor());
    }
}
