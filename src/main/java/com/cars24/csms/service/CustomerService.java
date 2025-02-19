package com.cars24.csms.service;

import com.cars24.csms.data.req.CreateCustomerReq;
import com.cars24.csms.data.resp.CreateCustomerResponce;

public interface CustomerService {
    CreateCustomerResponce createCustomer(CreateCustomerReq createCustomerReq);
    CreateCustomerResponce updateCustomer(int id,  CreateCustomerReq createCustomerReq);
    public CreateCustomerResponce getCustomer(int id);
    CreateCustomerResponce updateCustomer(CreateCustomerReq createCustomerReq);
    CreateCustomerResponce deleteCustomer(int id);
}
