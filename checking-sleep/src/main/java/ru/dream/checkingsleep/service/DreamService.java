package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface DreamService {


    DreamCreateDto createDream (DreamCreateDto dreamCreateDto);

    DreamUpdateDto updateDream (DreamUpdateDto dreamUpdateDto);

    void deleteDream (UUID id);

    public Map<LocalDate, List<DreamServiceImpl.SleepWakeInterval>> calculateSleepWakeIntervalsForDateRange(LocalDate startDate, LocalDate endDate);
    class SleepWakeInterval {
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean isSleep;
    }


}
