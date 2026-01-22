package com.app.SportsFieldManagement.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String username,@NotBlank String password) {
}
