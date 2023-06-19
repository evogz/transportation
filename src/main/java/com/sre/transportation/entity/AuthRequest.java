package com.sre.transportation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull
@Getter
@Setter
public class AuthRequest {
    @Size(max = 36)
    String username;
    @Size(max = 8)
    String password;
}
