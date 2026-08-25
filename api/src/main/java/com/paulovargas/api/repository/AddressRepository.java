package com.paulovargas.api.repository;

import com.paulovargas.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByClientId(Long clientId);
}
