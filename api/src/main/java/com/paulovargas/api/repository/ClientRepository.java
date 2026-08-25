package com.paulovargas.api.repository;

import com.paulovargas.api.entity.Client;
import com.paulovargas.api.entity.UserStatus;
import com.paulovargas.api.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByDocument(String document);

    List<Client> findByStatus(UserStatus status);

    List<Client> findByUserType(UserType userType);

    List<Client> findByStatusAndUserType(UserStatus status, UserType userType);
}
