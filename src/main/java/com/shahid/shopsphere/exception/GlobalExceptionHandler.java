package com.shahid.shopsphere.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shahid.shopsphere.dto.auth.ApiResponseDto;
import com.shahid.shopsphere.dto.responses.ErrorResponse;


import jakarta.servlet.http.HttpServletRequest;

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
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
        ResourceNotFoundException ex,
        HttpServletRequest request) {

    ErrorResponse error = ErrorResponse.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error);
}
@ExceptionHandler(CategoryAlreadyExistsException.class)
public ResponseEntity<ApiResponseDto<Void>> handleCategoryAlreadyExists(
        CategoryAlreadyExistsException ex) {

    ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
            .success(false)
            .message(ex.getMessage())
            .timeStamp(LocalDateTime.now())
            .build();

    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(response);
}
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponseDto<Void>> handleGlobalException(Exception ex) {

    ex.printStackTrace(); // Temporary for debugging

    ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
            .success(false)
            .message(ex.getMessage())
            .timeStamp(LocalDateTime.now())
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
}

   @ExceptionHandler(ProductAlreadyExistsException.class)
   public ResponseEntity<ApiResponseDto<Void>> handleProductAlreadyExists(ProductAlreadyExistsException ex)
   {
        ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
                                                                     .success(false)
                                                                     .message(ex.getMessage())
                                                                     .timeStamp(LocalDateTime.now())
                                                                     .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
   }

   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<ApiResponseDto<Void>> handleBadRequest(BadRequestException ex)
   {
        ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
                                                      .success(false)
                                                      .message(ex.getMessage())
                                                      .timeStamp(LocalDateTime.now())
                                                      .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
   }
   @ExceptionHandler(ForbiddenException.class)
   public ResponseEntity<ApiResponseDto<Void>> handleBadRequest(ForbiddenException ex)
   {
        ApiResponseDto<Void> response = ApiResponseDto.<Void>builder()
                                                      .success(false)
                                                      .message(ex.getMessage())
                                                      .timeStamp(LocalDateTime.now())
                                                      .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
   }
}

