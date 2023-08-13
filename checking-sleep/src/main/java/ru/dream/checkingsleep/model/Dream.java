package ru.dream.checkingsleep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user")
    private User user;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime finish;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
    private List<Tag> tags = new ArrayList<>();

}
