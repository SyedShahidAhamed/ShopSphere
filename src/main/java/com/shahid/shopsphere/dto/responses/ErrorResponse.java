package com.shahid.shopsphere.dto.responses;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timeStamp;

    private int status;

    private String error;

    private String message;

    private String path;
}
