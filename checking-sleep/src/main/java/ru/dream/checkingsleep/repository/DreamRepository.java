package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface DreamRepository extends JpaRepository<Dream, Long>, JpaSpecificationExecutor<Dream> {

    Optional<LocalDateTime> findDayFinishById(Long id);

    Optional<LocalDateTime> findDayStartById(Long id);

    Optional<Dream> findById(UUID id);

    void deleteById(UUID id);
    Optional<Dream> findByUser(UserDto user);
}
