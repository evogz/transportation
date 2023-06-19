package com.sre.transportation.security;

import com.sre.transportation.entity.UserCredentials;
import com.sre.transportation.service.UserCredentialsService;
import com.sre.transportation.service.impl.UserCredentialsServiceImpl;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
//TODO: exception handler

@Component
@NoArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private UserCredentialsServiceImpl userCredentialsService;
    @Autowired
    private JWTUtils jwtUtils;
    String token = null;

    public JWTFilter(JWTUtils utils) {
        this.jwtUtils= utils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7); //split bearer
            System.out.println("token: " + token);
            if(token != null && jwtUtils.validateToken(token)){
                Authentication auth = token!= null ?  jwtUtils.getAuthentication(token) : null;
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(request,response);
    }
}
