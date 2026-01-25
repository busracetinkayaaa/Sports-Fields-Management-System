package com.app.SportsFieldManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequest(@NotBlank(message = "Name cannot be empty") String name,
                            @NotBlank(message = "Phone cannot be empty") String phone,
                            @Email(message = "Invalid email format") @NotBlank(message = "Email cannot be empty") String email) {

}
