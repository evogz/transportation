package com.sre.transportation.service.impl;

import com.sre.transportation.entity.UserCredentials;
import com.sre.transportation.repository.UserCredentialsRepository;
import com.sre.transportation.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {
    @Autowired
    private UserCredentialsRepository ucr;
    @Override
    public UserCredentials getUserName(String username) throws  Exception{
        try{
            UserCredentials user  = ucr.findById(username).get();
            return user;
        }catch (Exception e){
            throw new Exception("getUserName ", e);
        }


    }

    public UserCredentials loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.ucr.findByUsername(username).get();
    }
}
