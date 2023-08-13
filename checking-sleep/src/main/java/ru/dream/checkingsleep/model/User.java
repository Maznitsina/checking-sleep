package ru.dream.checkingsleep.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(nullable = false)
    private String childName;

    @Column(nullable = false)
    private Long childAge;

    @Column
    private String momName;

    @Column
    private String dadName;

    @Column
    private String childPhoto;

    @Column
    private String momPhoto;

    @Column
    private String dadPhoto;

    @Column
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set <Role> roles = new HashSet<>();
}
