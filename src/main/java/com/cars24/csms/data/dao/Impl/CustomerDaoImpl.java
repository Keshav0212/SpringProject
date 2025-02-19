package com.cars24.csms.data.dao.Impl;

import com.cars24.csms.data.dao.CustomerDao;
import com.cars24.csms.data.entities.CustomerEntity;
import com.cars24.csms.data.repositories.CustomerRepository;
import com.cars24.csms.data.req.CreateCustomerReq;
import com.cars24.csms.data.resp.CreateCustomerResponce;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao {

    private final CustomerRepository customerRepository;

        @Override
        public int createCustomer(CreateCustomerReq createCustomerReq) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(createCustomerReq.getName());
        customerEntity.setPhone(createCustomerReq.getPhone());
        customerEntity.setEmail(createCustomerReq.getEmail());
        customerEntity.setAddress(createCustomerReq.getAddress());
        customerEntity.set_deleted(false);

        customerRepository.save(customerEntity); // Save customer to the database

        return 0; // Return the saved customer ID (or any other identifier)
    }

    @Override
    public int updateCustomer(int id, CreateCustomerReq createCustomerReq) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerEntity.setName(createCustomerReq.getName());
        customerEntity.setPhone(createCustomerReq.getPhone());
        customerEntity.setEmail(createCustomerReq.getEmail());
        customerEntity.setAddress(createCustomerReq.getAddress());

        customerRepository.save(customerEntity);

        return 0;
    }

    @Override
    public CreateCustomerResponce getCustomer(int id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        CreateCustomerResponce response = new CreateCustomerResponce();
        response.setName(customerEntity.getName());
        response.setPhone(customerEntity.getPhone());
        response.setEmail(customerEntity.getEmail());
        response.setAddress(customerEntity.getAddress());
        return response;


    }



    @Override
    @Transactional
    public CreateCustomerResponce deleteCustomer(int id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        CreateCustomerResponce response = new CreateCustomerResponce();
        response.setName(customerEntity.getName());
        response.setPhone(customerEntity.getPhone());
        response.setEmail(customerEntity.getEmail());
        response.setAddress(customerEntity.getAddress());
        customerEntity.set_deleted(true);
        customerRepository.save(customerEntity);
//        customerRepository.delete(customerEntity);
//        customerRepository.flush();
        return response;
    }


}
