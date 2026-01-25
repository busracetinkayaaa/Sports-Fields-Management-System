package com.app.SportsFieldManagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String username,@NotBlank String password) {
}
