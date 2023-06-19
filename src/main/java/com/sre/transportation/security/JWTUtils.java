package com.sre.transportation.security;

import com.sre.transportation.entity.UserCredentials;
import com.sre.transportation.repository.UserCredentialsRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String secretKey;
    private String tokenUserName;
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public String createToken(String username){
        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String userName = claims.getSubject();

            if(userName.equals("evrim")){ //TODO: dynamic
                return true;
            }else{
                return false;
            }
        }catch (JwtException | IllegalArgumentException e ){
            throw new JwtException("Invalid Token lalalala");
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        String username = claims.getSubject();

        //UserCredentials user = new UserCredentials(claims.getSubject(),"");
        UserCredentials user = userCredentialsRepository.findByUsername(username).get();
        Collection<? extends GrantedAuthority> aa = user.getAuthorities(user.getRoles(), user.getPrivileges());
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities(user.getRoles(), user.getPrivileges()));
    }
}
