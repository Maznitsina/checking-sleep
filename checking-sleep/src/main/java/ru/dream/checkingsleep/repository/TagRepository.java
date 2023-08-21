package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.Tag;

import java.util.Optional;
import java.util.UUID;


public interface TagRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findById(UUID id);

    Optional<Tag> findByDream(Dream dream);
}
