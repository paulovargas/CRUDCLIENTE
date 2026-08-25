package com.paulovargas.api.dto;

import com.paulovargas.api.entity.UserStatus;
import com.paulovargas.api.entity.UserType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponse {

    private Long id;

    private String name;

    private String tradeName;

    private String email;

    private String document;

    private String phone;

    private UserType userType;

    private UserStatus status;

    private boolean sellerApproved;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
