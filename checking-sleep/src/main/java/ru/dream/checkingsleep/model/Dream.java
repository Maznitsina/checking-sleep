package ru.dream.checkingsleep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "dream")

public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user")
    private User user;

    @Column
    private LocalDateTime dayStart;

    @Column
    private LocalDateTime dayFinish;

    @Column
    private LocalDateTime nightStart;

    @Column
    private LocalDateTime nightFinish;

    @OneToOne
    private Comment comment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
    private List<Tag> tags = new ArrayList<>();

}
