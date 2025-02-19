package com.cars24.csms.service.impl;

import com.cars24.csms.data.dao.Impl.AppUserDoaImpl;
import com.cars24.csms.data.entities.AppUserEntity;
import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignupRequest;
import com.cars24.csms.data.resp.APIResponse;
import com.cars24.csms.data.resp.LoginResp;
import com.cars24.csms.exceptions.UserServiceException;
import com.cars24.csms.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cars24.csms.data.repositories.AppUserRepository;

@RequiredArgsConstructor
@Service

public class AppUserServiceImpl implements AppUserService {

    private final AppUserDoaImpl appUserDoaImpl;
    private final AppUserRepository appUserRepository;

    @Override
    public LoginResp getAppUserDetails(LoginRequest loginRequest) {
        AppUserEntity appUserEntity = appUserDoaImpl.getAppUserDetails(loginRequest);

        LoginResp loginResp = new LoginResp();
        loginResp.setId(appUserEntity.getUser_id());
        loginResp.setUsername(appUserEntity.getUsername());

        return loginResp;
    }

//    @Override
    public ResponseEntity<APIResponse> createUser(SignupRequest signupRequest) {

        if (appUserRepository.existsByUsername(signupRequest.getUsername())) {
            // Throw the custom exception instead of manually setting the response
            throw new UserServiceException("User already exists, please login.");
        }

        // Proceed with user creation
        appUserDoaImpl.createUser(signupRequest);

        // Prepare the success response
       APIResponse resp = new APIResponse();
        resp.setStatuscode(HttpStatus.OK.value());
        resp.setSuccess(true);
        resp.setMessage("User signed up successfully");
        resp.setService("APPUSR - " + HttpStatus.OK.value());
        resp.setData(null);

        return ResponseEntity.ok().body(resp);
    }


}
