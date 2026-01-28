package com.app.SportsFieldManagement.dto.response;

import com.app.SportsFieldManagement.model.SportField;

import java.math.BigDecimal;

public record SportFieldResponse(Long id, String name, SportField.FieldType sportType,
                                 BigDecimal price,boolean indoor) {
}
