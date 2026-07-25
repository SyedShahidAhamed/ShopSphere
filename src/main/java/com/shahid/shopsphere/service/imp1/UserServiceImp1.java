package com.shahid.shopsphere.service.imp1;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shahid.shopsphere.dto.auth.LoginRequest;
import com.shahid.shopsphere.dto.auth.LoginResponse;
import com.shahid.shopsphere.dto.auth.RegisterRequest;
import com.shahid.shopsphere.entity.Role;
import com.shahid.shopsphere.entity.User;
import com.shahid.shopsphere.exception.InvalidCredentialsException;
import com.shahid.shopsphere.exception.UserAlreadyExistsException;
import com.shahid.shopsphere.repository.UserRepository;
import com.shahid.shopsphere.service.CustomUserDetailsService;
import com.shahid.shopsphere.service.JwtService;
import com.shahid.shopsphere.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp1 implements UserService{

    private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
   private final CustomUserDetailsService customUserDetailsService; 
   private final JwtService jwtService;
   

    @Override
    public void register(RegisterRequest request) { 
       //check if email already exist
       if(userRepository.existsByEmail(request.getEmail())){
           throw new  UserAlreadyExistsException("Email Already Exists.");
       }

       //create user object
       User user = new User();
       user.setName(request.getName());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setEmail(request.getEmail());
       user.setRole(Role.USER);
       
       userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request)
    { //main
      try{
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
      );
      }catch(BadCredentialsException ex)
      {
         throw new InvalidCredentialsException("Invalid email or password");
       }
      
           //extract user
           User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("user not found."));
           
           //extract user details
           UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

             String token = jwtService.generateToken(userDetails);
             return new LoginResponse(token);

    }
}
