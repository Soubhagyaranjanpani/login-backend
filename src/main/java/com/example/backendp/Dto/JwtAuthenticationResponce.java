package com.example.backendp.Dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponce {
    private String token;
    private String refreshToken;
}
