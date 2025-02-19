package com.cars24.csms.controllers;

import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignupRequest;
import com.cars24.csms.data.resp.APIResponse;
import com.cars24.csms.data.resp.LoginResp;
import com.cars24.csms.service.impl.AppUserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Service
@RequiredArgsConstructor

public class AppUserController {

    private final AppUserServiceImpl appUserServiceImpl;

    @GetMapping("/login")
    public ResponseEntity<LoginResp> getUser(@Valid @RequestBody LoginRequest loginRequest){

        LoginResp loginResp = appUserServiceImpl.getAppUserDetails(loginRequest);
        return ResponseEntity.ok().body(loginResp);
        //return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> createUser(@Valid @RequestBody SignupRequest signupRequest){
        log.info(signupRequest.getUsername());
        log.info(signupRequest.getPassword());

        return appUserServiceImpl.createUser(signupRequest);

    }

}

