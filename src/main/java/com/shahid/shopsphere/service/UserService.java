package com.shahid.shopsphere.service;

import com.shahid.shopsphere.dto.auth.LoginRequest;
import com.shahid.shopsphere.dto.auth.LoginResponse;
import com.shahid.shopsphere.dto.auth.RegisterRequest;


public interface UserService {
    void register(RegisterRequest request) ;
    LoginResponse login(LoginRequest request) ;
}
