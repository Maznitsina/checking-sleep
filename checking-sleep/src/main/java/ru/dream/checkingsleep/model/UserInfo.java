package ru.dream.checkingsleep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Setter
@Table(name = "userInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(nullable = false)
    private String childName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column
    private String temperament;

    @Column
    private String momName;

    @Column
    private String dadName;

    @Column
    private String childPhotoId;

    @Column
    private String momPhotoId;

    @Column
    private String dadPhotoId;

}
