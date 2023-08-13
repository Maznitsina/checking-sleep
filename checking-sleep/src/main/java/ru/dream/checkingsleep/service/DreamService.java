package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamDto;

@Service
public interface DreamService {

    DreamDto getDreamById(Long id);

    DreamDto getStartById(Long id);

    DreamDto getFinishById(Long id);
}
