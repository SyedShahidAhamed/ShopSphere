package com.shahid.shopsphere.service;

import com.shahid.shopsphere.dto.LoginRequest;
import com.shahid.shopsphere.dto.LoginResponse;
import com.shahid.shopsphere.dto.RegisterRequest;


public interface UserService {
    void register(RegisterRequest request) ;
    LoginResponse login(LoginRequest request) ;
}
