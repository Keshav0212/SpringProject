package com.cars24.csms.service;

import com.cars24.csms.data.entities.AppUserEntity;

public interface LoginService {
     AppUserEntity findAppUserDetailsByUNandPass(String UN, String password);
}
