package com.example.backendp.controller;

import com.example.backendp.Dto.JwtAuthenticationResponce;
import com.example.backendp.Dto.RefreshTokenRequest;
import com.example.backendp.Dto.SignInRequest;
import com.example.backendp.Dto.SignUpRequest;
import com.example.backendp.model.User;
import com.example.backendp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponce> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponce> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
