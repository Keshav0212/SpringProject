package com.cars24.csms.controllers;

import com.cars24.csms.data.dao.CustomerDao;
import com.cars24.csms.data.entities.CustomerEntity;
import com.cars24.csms.data.repositories.CustomerRepository;
import com.cars24.csms.data.req.CreateCustomerReq;
import com.cars24.csms.data.resp.CreateCustomerResponce;
import com.cars24.csms.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
@Validated // Ensure validation happens at controller level
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerDao customerDao;

    @PostMapping
    public ResponseEntity<CreateCustomerResponce> createCustomer(@Valid @RequestBody CreateCustomerReq createCustomerReq) {
        CreateCustomerResponce createCustomerResponce = new CreateCustomerResponce();
        log.info("[createCustomer] createCustomerReq {}", createCustomerReq);

        customerService.createCustomer(createCustomerReq);
        createCustomerResponce.setName(createCustomerReq.getName());
        createCustomerResponce.setPhone(createCustomerReq.getPhone());
        createCustomerResponce.setEmail(createCustomerReq.getEmail());
        createCustomerResponce.setAddress(createCustomerReq.getAddress());
            return ResponseEntity.ok().body(createCustomerResponce);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CreateCustomerResponce> updateCustomer(@PathVariable int id, @Valid @RequestBody CreateCustomerReq createCustomerReq) {

            log.info("[updateCustomer] Updating customer with id: {} , createCustomerReq: {}", id, createCustomerReq);
            CreateCustomerResponce response = new CreateCustomerResponce();
            customerService.updateCustomer(id, createCustomerReq);
            response.setName(createCustomerReq.getName());
            response.setPhone(createCustomerReq.getPhone());
            response.setEmail(createCustomerReq.getEmail());
            response.setAddress(createCustomerReq.getAddress());

            return ResponseEntity.ok().body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerResponce> getCustomer(@PathVariable int id) {
        log.info("[getCustomerById] id: {}", id);
        customerService.getCustomer(id);
        CreateCustomerResponce response = customerDao.getCustomer(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        try {
            CreateCustomerResponce customer = customerService.deleteCustomer(id);
            log.info("[deleteCustomer] deleted the customer with  id: {}", id);
            return ResponseEntity.ok(customer);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

}
