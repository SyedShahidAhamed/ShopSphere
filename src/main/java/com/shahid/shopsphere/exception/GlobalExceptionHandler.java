package com.shahid.shopsphere.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shahid.shopsphere.dto.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
   
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleuserExists(UserAlreadyExistsException ex)
    {
        ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
                                             .success(false)
                                             .message(ex.getMessage())
                                             .timeStamp(LocalDateTime.now())
                                             .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
public ResponseEntity<ApiResponseDto<Void>> handleInvalidCredentials(
        InvalidCredentialsException ex) {

    ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
            .success(false)
            .message(ex.getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(response);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponseDto<Map<String, String>>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );

    ApiResponseDto<Map<String, String>> response =
            ApiResponseDto.<Map<String, String>>builder()
                    .success(false)
                    .message("Validation Failed")
                    .data(errors)
                    .build();

    return ResponseEntity.badRequest().body(response);
}
}
