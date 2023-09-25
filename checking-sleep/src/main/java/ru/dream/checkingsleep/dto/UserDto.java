package ru.dream.checkingsleep.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private UUID id;
    private String mail;
    private String password;
    private String childName;
    private LocalDate dateOfBirth;
    private String temperament;
    private String momName;
    private String dadName;
    private String childPhotoId;
    private String momPhotoId;
    private String dadPhotoId;
}
