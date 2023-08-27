package ru.dream.checkingsleep.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.DreamRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class DreamServiceImpl implements DreamService {
    private final DreamRepository dreamRepository;
    private final DreamMapper dreamMapper;

    @Override
    public DreamCreateDto createDream(DreamCreateDto dreamCreateDto) {
        Dream dream = dreamRepository.save(dreamMapper.toCreateEntity(dreamCreateDto));
        return dreamMapper.toCreateDto(dream);
    }

    @Override
    public DreamUpdateDto updateDream(DreamUpdateDto dreamUpdateDto) {
        Dream dream = dreamRepository.findById(dreamUpdateDto.getId()).orElseThrow();
        DreamUpdateDto dto = dreamMapper.toUpdateDto(dream);
        Dream dream1 = dreamMapper.toUpdateEntity(dto);
        Dream dream2 = dreamRepository.save(dream1);
        return dreamMapper.toUpdateDto(dream2);
    }

    @Override
    public void deleteDream(UUID id) {
        dreamRepository.deleteById(id);
    }

    public Map<LocalDate, List<SleepWakeInterval>> calculateSleepWakeIntervalsForDateRange(LocalDate startDate, LocalDate endDate) {
        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(Duration.between(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay()).toDays())
                .collect(Collectors.toMap(
                        date -> date,
                        this::calculateSleepWakeIntervals
                ));
    }

    public List<SleepWakeInterval> calculateSleepWakeIntervals(LocalDate date) {
        List<Dream> dreams = dreamRepository.findByDate(date);

        List<SleepWakeInterval> intervals = new ArrayList<>();
        boolean[] isNightSleepArray = {true};
        LocalDateTime[] previousEndTimeArray = {LocalDateTime.of(date, LocalTime.MIDNIGHT)};

        if (dreams.isEmpty()) {
            intervals.add(new SleepWakeInterval(!isNightSleepArray[0], "Бодрствование", (int) Duration.between(previousEndTimeArray[0], LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)).toMinutes()));
        } else {
            intervals = dreams.stream()
                    .flatMap(dream -> {
                        List<SleepWakeInterval> currentIntervals = new ArrayList<>();
                        int awakeMinutes = calculateAwakeMinutes(dream.getDayStart(), dream.getDayFinish(), dream.getNightStart(), dream.getNightFinish());

                        if (dream.getDayStart().isAfter(previousEndTimeArray[0])) {
                            currentIntervals.add(new SleepWakeInterval(isNightSleepArray[0], "Бодрствование", (int) Duration.between(previousEndTimeArray[0], dream.getDayStart()).toMinutes()));
                        }

                        int sleepMinutes = (int) Duration.between(dream.getDayStart(), dream.getDayFinish()).toMinutes();
                        String intervalType = isNightSleepArray[0] ? "Ночной сон" : "Дневной сон";
                        currentIntervals.add(new SleepWakeInterval(!isNightSleepArray[0], intervalType, sleepMinutes));

                        isNightSleepArray[0] = !isNightSleepArray[0];
                        previousEndTimeArray[0] = dream.getDayFinish();

                        return currentIntervals.stream();
                    })
                    .collect(Collectors.toList());

            if (previousEndTimeArray[0].isBefore(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))) {
                String lastIntervalType = isNightSleepArray[0] ? "Ночной сон" : "Дневной сон";
                intervals.add(new SleepWakeInterval(isNightSleepArray[0], lastIntervalType, (int) Duration.between(previousEndTimeArray[0], LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)).toMinutes()));
            }
        }


        return intervals;
    }

    public int calculateAwakeMinutes(LocalDateTime dayStart, LocalDateTime dayFinish, LocalDateTime nightStart, LocalDateTime nightFinish) {
        int awakeMinutes = 0;

        if (dayStart != null && dayFinish != null) {
            awakeMinutes += (int) Duration.between(dayFinish, dayStart).toMinutes();
        }

        if (nightStart != null && nightFinish != null) {
            awakeMinutes += (int) Duration.between(nightFinish, nightStart).toMinutes();
        }

        if (nightStart != null && dayStart != null && nightStart.isBefore(dayStart)) {
            awakeMinutes += (int) Duration.between(nightStart, dayStart).toMinutes();
        }

        return awakeMinutes;
    }

    public class SleepWakeInterval {
        private boolean isSleep;
        private String intervalType;
        private int durationMinutes;

        public SleepWakeInterval(boolean isSleep, String intervalType, int durationMinutes) {
            this.isSleep = isSleep;
            this.intervalType = intervalType;
            this.durationMinutes = durationMinutes;
        }
    }
}


