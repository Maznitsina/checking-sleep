package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.checkingsleep.model.Comment;
import ru.dream.checkingsleep.model.Dream;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findById(UUID id);

    Optional<Comment> findByDream(Dream dream);
}
