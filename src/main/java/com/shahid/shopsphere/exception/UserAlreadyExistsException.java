package com.shahid.shopsphere.exception;

public class UserAlreadyExistsException extends RuntimeException{
    
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
