package ru.dream.checkingsleep.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface DreamService {

    DreamDto getDreamById(Long id);

    List<LocalDateTime> getDayStartById(Long id);

    List<LocalDateTime> getDayFinishById(Long id);

    DreamDto createDream (DreamCreateDto dreamCreateDto);

    DreamDto updateDream (DreamUpdateDto dreamUpdateDto);

    void deleteDream (UUID id);

    List<DreamDto> getAllDreams();

    List<DreamDto> getDreamByUser(UserDto user);
}
