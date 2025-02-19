package com.cars24.csms.service.impl;

import com.cars24.csms.data.dao.AppUserDao;
import com.cars24.csms.data.entities.AppUserEntity;
import com.cars24.csms.data.req.LoginRequest;
import com.cars24.csms.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginImpl implements LoginService {
    private final AppUserDao appUserDao;

    @Override
    public AppUserEntity findAppUserDetailsByUNandPass(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return appUserDao.getAppUserDetails(loginRequest);
    }
}
