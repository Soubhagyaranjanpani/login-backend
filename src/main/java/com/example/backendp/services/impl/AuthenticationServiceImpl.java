package com.example.backendp.services.impl;

import com.example.backendp.Dto.JwtAuthenticationResponce;
import com.example.backendp.Dto.RefreshTokenRequest;
import com.example.backendp.Dto.SignInRequest;
import com.example.backendp.Dto.SignUpRequest;
import com.example.backendp.model.Role;
import com.example.backendp.model.User;
import com.example.backendp.repository.UserRepo;
import com.example.backendp.services.AuthenticationService;
import com.example.backendp.services.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

     public User signup (SignUpRequest  signUpRequest){
         User user=new User();

         user.setEmail(signUpRequest.getEmail());
         user.setUserId(signUpRequest.getUserId());
         user.setName(signUpRequest.getName());
         user.setPhoneNumber(signUpRequest.getPhoneNumber());
         user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
         user.setGender(signUpRequest.getGender());
         user.setRole(Role.USER);
         return userRepo.save(user);
     }
     public JwtAuthenticationResponce signIn(SignInRequest signInRequest){
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
         var user=userRepo.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("invalid email and password"));
         var jwt=jwtService.generateToken(user);
         var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);

         JwtAuthenticationResponce jwtAuthenticationResponce=new JwtAuthenticationResponce();
         jwtAuthenticationResponce.setToken(jwt);
         jwtAuthenticationResponce.setRefreshToken(refreshToken);
         return jwtAuthenticationResponce;
     }
     public JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest){
         String userEmail=jwtService.extractUserName(refreshTokenRequest.getToken());
         User user=userRepo.findByEmail(userEmail).orElseThrow();
         if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
             var jwt=jwtService.generateToken(user);
             JwtAuthenticationResponce jwtAuthenticationResponce=new JwtAuthenticationResponce();
             jwtAuthenticationResponce.setToken(jwt);
             jwtAuthenticationResponce.setRefreshToken(refreshTokenRequest.getToken());
             return jwtAuthenticationResponce;
         }
         return null;
     }
}
