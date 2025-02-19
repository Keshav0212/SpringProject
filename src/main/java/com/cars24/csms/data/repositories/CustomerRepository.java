package com.cars24.csms.data.repositories;

import com.cars24.csms.data.entities.CustomerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {


    boolean existsByEmail(@Email @NotBlank String email);
    // Custom query methods can be added here
}
