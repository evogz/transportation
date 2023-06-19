package com.sre.transportation.service;

import com.sre.transportation.entity.UserCredentials;

public interface UserCredentialsService {
    UserCredentials getUserName(String username) throws Exception;
    //UserCredentials getUserByUserName(String userName);
    //UserCredentials getUserId(Long id);
}
