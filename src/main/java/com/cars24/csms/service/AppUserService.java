package com.cars24.csms.service;

import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignupRequest;
import com.cars24.csms.data.resp.APIResponse;
import com.cars24.csms.data.resp.LoginResp;
import org.springframework.http.ResponseEntity;

public interface AppUserService {

    public LoginResp getAppUserDetails(LoginRequest loginRequest);
    public ResponseEntity<APIResponse> createUser(SignupRequest signupRequest);

}
