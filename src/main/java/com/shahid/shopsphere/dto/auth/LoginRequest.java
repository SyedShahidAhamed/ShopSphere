
package com.shahid.shopsphere.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
     @NotBlank(message="email is required")
     @Email(message="invalid email")
     @Schema(description="Registered email",example="shahid@example.com")
    private String email;
    @NotBlank(message="Password is required")
    @Schema(description="Registered User password",example="password@123")
    private  String password;
}
