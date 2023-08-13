package ru.dream.checkingsleep.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.dream.checkingsleep.model.Role;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String mail;
    private List<Role> roles;
}
