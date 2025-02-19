package com.cars24.csms.data.req;

import com.cars24.csms.service.impl.CustomerServiceImpl;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateCustomerReq {

    private final CustomerServiceImpl customerServiceImpl;

    @Min(value = 1, message = "invalid Customer id")
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private String phone;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private String address;
}
