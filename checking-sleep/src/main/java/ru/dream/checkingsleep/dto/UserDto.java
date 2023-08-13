package ru.dream.checkingsleep.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private long id;
    private String mail;
    private String password;
    private String roles;
    private String child_name;
    private Long child_age;
    private String mom_name;
    private String dad_name;
    private String child_photo;
    private String mom_photo;
    private String dad_photo;
}
