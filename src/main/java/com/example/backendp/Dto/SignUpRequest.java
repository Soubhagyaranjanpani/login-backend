package com.example.backendp.Dto;

import com.example.backendp.model.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SignUpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String gender;

    private Role role;
}
