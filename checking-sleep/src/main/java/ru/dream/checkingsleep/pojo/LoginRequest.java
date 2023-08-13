package ru.dream.checkingsleep.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String mail;
    private String password;
}
