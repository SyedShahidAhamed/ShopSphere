package com.shahid.shopsphere.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message="name can't be null.")
    private String name;
   @NotBlank(message="password can't be null")
   @Size(min=6,message="password must contain atleast 6 letters")
    private String password;
    @NotBlank(message="email can't be null")
    @Email(message="invalid email")
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