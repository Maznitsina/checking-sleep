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
@Table(name = "user")
public class User {

    private MongoPhoto mongoPhoto;

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
    private LocalDate dateOfBirght;

    @Column
    private String temperament;

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

/*    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();*/
}
