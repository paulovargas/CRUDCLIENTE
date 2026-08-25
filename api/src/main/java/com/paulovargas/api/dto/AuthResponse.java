package com.paulovargas.api.dto;

import com.paulovargas.api.entity.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String token;

    private String tokenType = "Bearer";

    private Long expiresIn;

    private Long clientId;

    private String name;

    private String email;

    private UserType userType;
}
