package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface DreamService {

    DreamDto getDreamById(UUID id);

    List<LocalDateTime> getDayStartById(UUID id);

    List<LocalDateTime> getDayFinishById(UUID id);
    List<LocalDateTime> getNightStartById(UUID id);

    List<LocalDateTime> getNightFinishById(UUID id);

    DreamCreateDto createDream (DreamCreateDto dreamCreateDto);

    DreamUpdateDto updateDream (DreamUpdateDto dreamUpdateDto);

    void deleteDream (UUID id);

    List<DreamDto> getAllDreams();

    List<DreamDto> getDreamByUser(UserDto user);

   // Map<LocalDateTime, Double> getDiagrams(LocalDateTime dayStart, LocalDateTime dayFinish);
}
