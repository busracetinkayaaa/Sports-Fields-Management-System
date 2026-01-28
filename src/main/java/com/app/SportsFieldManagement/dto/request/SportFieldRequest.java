package com.app.SportsFieldManagement.dto.request;

import com.app.SportsFieldManagement.model.SportField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SportFieldRequest(@NotBlank String name, @NotNull SportField.FieldType sportType,
                                @Positive BigDecimal price, boolean indoor,
                                double latitude, double longitude) { }
