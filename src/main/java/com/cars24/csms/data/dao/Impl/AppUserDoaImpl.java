package com.cars24.csms.data.dao.Impl;

import com.cars24.csms.data.dao.AppUserDao;
import com.cars24.csms.data.entities.AppUserEntity;
import com.cars24.csms.data.repositories.AppUserRepository;

import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.data.req.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserDoaImpl implements AppUserDao {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUserEntity getAppUserDetails(LoginRequest loginRequest) {
        return appUserRepository.findAppUserDetailsByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public void createUser(SignupRequest signupRequest) {

//        // appUserEntity.setUser_id(10);
//        appUserEntity.setActive(true);
//        appUserEntity.setPassword(signupRequest.getPassword());
//        appUserEntity.setUsername(signupRequest.getUsername());

        ObjectMapper objectMapper = new ObjectMapper();
        AppUserEntity appUserEntity = objectMapper.convertValue(signupRequest , AppUserEntity.class);
        appUserEntity.setActive(true);

        appUserRepository.save(appUserEntity);

    }

}
