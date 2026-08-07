package com.shahid.shopsphere.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.auth.ApiResponseDto;
import com.shahid.shopsphere.dto.auth.LoginRequest;
import com.shahid.shopsphere.dto.auth.LoginResponse;
import com.shahid.shopsphere.dto.auth.RegisterRequest;
import com.shahid.shopsphere.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")//base url
@Tag(
    name = "Authentication",
    description = "User Registration and Login APIs"
)

public class AuthController {
    private final UserService userService;
    
  
@Operation(summary = "Register a new User")
@ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "201",
        description = "User registered successfully"
    ),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "409",
        description = "Email already exists"
    ),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "400",
        description = "Validation failed"
    )
})
     
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<Void>> register(@Valid @RequestBody RegisterRequest request) 
    {
         userService.register(request);
        //response
          ApiResponseDto<Void> response = ApiResponseDto.<Void>builder().success(true).message(" User Registeration Sucessfull").build();
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
    summary = "Authenticate user",
    description = "Authenticates a user and returns a JWT token."
)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid email or password"),
        @ApiResponse(responseCode = "400", description = "Validation failed")
    })

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponse>> login(@Valid @RequestBody LoginRequest request)
    {
        
        LoginResponse loginresponse =  userService.login(request);
        //response
        ApiResponseDto<LoginResponse> response =  ApiResponseDto.<LoginResponse>builder().success(true).message("Login Successfull").data(loginresponse).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public ResponseEntity<ApiResponseDto<String>> hello()
    {
       ApiResponseDto<String> response = ApiResponseDto.<String>builder().success(true).message("Request Successful").data("Hello! Welcome to ShopSphere").build();
       return ResponseEntity.ok(response);
    }
}
