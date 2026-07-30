package com.shahid.shopsphere.dto.auth;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response wrapper")
public class ApiResponseDto<T> {

    @Schema(
        description = "Indicates whether the request was successful",
        example = "true"
    )
    private boolean success;

    @Schema(
        description = "Response message",
        example = "Login successful"
    )
    private String message;

    @Schema(
        description = "Response payload. This field is null if no data is returned."
    )
    private T data;

    @Builder.Default
    @Schema(
        description = "Timestamp when the response was generated",
        example = "2026-07-30T16:30:45"
    )
    
    private LocalDateTime timeStamp = LocalDateTime.now();
}