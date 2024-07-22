package com.example.backendp.services;

import com.example.backendp.Dto.JwtAuthenticationResponce;
import com.example.backendp.Dto.RefreshTokenRequest;
import com.example.backendp.Dto.SignInRequest;
import com.example.backendp.Dto.SignUpRequest;
import com.example.backendp.model.User;

public interface AuthenticationService {

    User signup (SignUpRequest signUpRequest);

    JwtAuthenticationResponce signIn(SignInRequest signInRequest);

    JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest);
}
