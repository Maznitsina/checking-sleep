package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.model.Dream;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DreamRepository extends JpaRepository<Dream, UUID> {

    Optional<LocalDateTime> findDayFinishById(UUID id);

    Optional<LocalDateTime> findDayStartById(UUID id);
    Optional<LocalDateTime> findNightFinishById(UUID id);

    Optional<LocalDateTime> findNightStartById(UUID id);

    Optional<Dream> findById(UUID id);

    void deleteById(UUID id);
    Optional<Dream> findByUser(UserDto user);


    List<Dream> findAllByDayStartIsBeforeAndDayFinishIsAfter(LocalDateTime dayStart, LocalDateTime dayFinish);
}
