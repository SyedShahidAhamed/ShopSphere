package com.shahid.shopsphere.dto.auth;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto <T> {
    
    private boolean success;
    private String message;
    private T data;
    @Builder.Default
    private LocalDateTime  timeStamp = LocalDateTime.now(); 
}


/*
register
{
  "success": false,
  "message": "Invalid email or password",
  "timestamp": "2026-07-23T18:11:45"
} */

/* login
{
    "success": true,
    "message": "Login Successful",
    "data": {
        "token": "eyJhbGc..."
    },
    "timestamp": "2026-07-23T20:30:00"
} */

/*
error
{
    "success": false,
    "message": "Invalid Credentials",
    "data": null,
    "timestamp": "2026-07-23T20:30:00"
}
 */