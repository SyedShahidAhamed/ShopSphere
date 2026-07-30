package com.shahid.shopsphere.dto.responses;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Standard error response returned when an API request fails")
public class ErrorResponse {

    @Schema(
        description = "Timestamp when the error occurred",
        example = "2026-07-30T16:30:45"
    )
    private LocalDateTime timeStamp;

    @Schema(
        description = "HTTP status code",
        example = "404"
    )
    private int status;

    @Schema(
        description = "HTTP error name",
        example = "Not Found"
    )
    private String error;

    @Schema(
        description = "Detailed error message",
        example = "Product with ID 10 not found."
    )
    private String message;

    @Schema(
        description = "API endpoint that caused the error",
        example = "/api/products/10"
    )
    private String path;
}