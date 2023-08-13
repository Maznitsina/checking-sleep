package ru.dream.checkingsleep.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    private String mail;
    private Set<String> roles;
    private String password;
}
