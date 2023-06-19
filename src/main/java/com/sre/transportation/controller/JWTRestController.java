package com.sre.transportation.controller;

import com.sre.transportation.entity.AuthRequest;
import com.sre.transportation.entity.AuthResponse;
import com.sre.transportation.entity.UserCredentials;
import com.sre.transportation.repository.UserCredentialsRepository;
import com.sre.transportation.security.JWTUtils;
import com.sre.transportation.service.impl.UserCredentialsServiceImpl;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


@RestController
public class JWTRestController {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserCredentialsServiceImpl userCredentialsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest req) throws Exception {
        try {
            String username = req.getUsername();
            final String token  = jwtUtils.createToken(username);
            return ok(token);
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }catch (StackOverflowError ex){
            ex.printStackTrace();
            throw new Exception("createToken");
        }

    }


    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e){
            throw new Exception("Invalid credentials",e);
        }catch (Exception ex){
            throw new Exception("Unknown ", ex);
        }
    }
}
