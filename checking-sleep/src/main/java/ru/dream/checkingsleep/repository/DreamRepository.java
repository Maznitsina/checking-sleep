package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.User;


import java.time.LocalDate;
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
    List <Dream> findByUser(UserDto user);
    List<Dream> findByDate(LocalDate date);
    List<Dream> findAllByDayStartIsBeforeAndDayFinishIsAfter(LocalDateTime dayStart, LocalDateTime dayFinish);
}
