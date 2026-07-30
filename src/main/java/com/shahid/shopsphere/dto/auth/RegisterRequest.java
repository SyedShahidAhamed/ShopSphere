package com.shahid.shopsphere.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message="name can't be null.")
    @Schema(description="Registeration Username",example="Alex")
    private String name;

   @NotBlank(message="password can't be null")
   @Size(min=6,message="password must contain atleast 6 letters")
   @Schema(description="Registeration Userpassword",example="password@123")
    private String password;

    @NotBlank(message="email can't be null")
    @Email(message="invalid email")
     @Schema(description="Registeration email",example="shahid@example.com")
    private String email;
}
/*
📖 Let's understand the annotations
@Data

Instead of writing:

getName()

setName()

getEmail()

setEmail()

toString()

equals()

hashCode()

Lombok generates all of them automatically. */