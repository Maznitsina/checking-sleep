package ru.dream.checkingsleep.service.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.mappers.DreamMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.DreamRepository;
import ru.dream.checkingsleep.dto.SleepWakeInterval;
import ru.dream.checkingsleep.service.DreamService;

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
        Dream dream = dreamRepository.findById(dreamUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException
                        ("Сущность Dream с id " + dreamUpdateDto.getId() + " не найдена")
                );
        dreamMapper.updateDreamFromDto(dreamUpdateDto, dream);
        Dream updatedDream = dreamRepository.save(dream);
        return dreamMapper.toUpdateDto(updatedDream);
    }

    @Override
    public void deleteDream(UUID id) {
        dreamRepository.deleteById(id);
    }

    @Override
    public Map<LocalDate, List<SleepWakeInterval>> calculateSleepWakeIntervalsForDateRange(
            LocalDate startDate, LocalDate endDate
    ) {
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
        boolean isNightSleepArray = true;
        LocalDateTime previousEndTimeArray = LocalDateTime.of(date, LocalTime.MIDNIGHT);

        if (dreams.isEmpty()) {
            intervals.add(
                    new SleepWakeInterval(!isNightSleepArray, "Бодрствование", (int) Duration
                            .between(previousEndTimeArray, LocalDateTime.of(
                                    date.plusDays(1), LocalTime.MIDNIGHT)).toMinutes()));
        } else {
            for (Dream dream : dreams) {
                List<SleepWakeInterval> currentIntervals = new ArrayList<>();
                int awakeMinutes = calculateAwakeMinutes(
                        dream.getDayStart(), dream.getDayFinish(), dream.getNightStart(), dream.getNightFinish());

                if (dream.getDayStart().isAfter(previousEndTimeArray)) {
                    currentIntervals.add(new SleepWakeInterval(
                            isNightSleepArray, "Бодрствование", (int) Duration.between(
                            previousEndTimeArray, dream.getDayStart()).toMinutes()));
                }

                int sleepMinutes = (int) Duration.between(dream.getDayStart(), dream.getDayFinish()).toMinutes();
                String intervalType = isNightSleepArray ? "Ночной сон" : "Дневной сон";
                currentIntervals.add(new SleepWakeInterval(!isNightSleepArray, intervalType, sleepMinutes));

                isNightSleepArray = !isNightSleepArray;
                previousEndTimeArray = dream.getDayFinish();

                intervals.addAll(currentIntervals);
            }

            if (previousEndTimeArray.isBefore(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))) {
                String lastIntervalType = isNightSleepArray ? "Ночной сон" : "Дневной сон";
                intervals.add(new SleepWakeInterval(
                        isNightSleepArray,
                        lastIntervalType,
                        (int) Duration.between(
                                        previousEndTimeArray,
                                        LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))
                                .toMinutes()));
            }
        }

        return intervals;
    }

    public int calculateAwakeMinutes(
            LocalDateTime dayStart,
            LocalDateTime dayFinish,
            LocalDateTime nightStart,
            LocalDateTime nightFinish
    ) {
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
    /*public List<SleepWakeIntervalWithTagAndComment> calculateSleepWakeIntervalsWithTagsAndComments(LocalDate date) {
        List<Dream> dreams = dreamRepository.findByDate(date);

        List<SleepWakeIntervalWithTagAndComment> intervals = new ArrayList<>();
        boolean isNightSleepArray = true;
        LocalDateTime previousEndTimeArray = LocalDateTime.of(date, LocalTime.MIDNIGHT);

        if (dreams.isEmpty()) {
            intervals.add(new SleepWakeIntervalWithTagAndComment(!isNightSleepArray, "Бодрствование",
                    (int) Duration.between(previousEndTimeArray, LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)).toMinutes(),
                    null, null));
        } else {
            for (Dream dream : dreams) {
                List<SleepWakeIntervalWithTagAndComment> currentIntervals = new ArrayList<>();
                LocalDateTime dayStart = dream.getDayStart();
                LocalDateTime dayFinish = dream.getDayFinish();
                LocalDateTime nightStart = dream.getNightStart();
                LocalDateTime nightFinish = dream.getNightFinish();

                int totalAwakeMinutes = calculateTotalAwakeMinutes(dayStart, dayFinish, nightStart, nightFinish);

                if (dayStart.isAfter(previousEndTimeArray)) {
                    intervals.add(new SleepWakeIntervalWithTagAndComment(
                            isNightSleepArray, "Бодрствование", (int) Duration.between(
                            previousEndTimeArray, dayStart).toMinutes(), null, null));
                }

                int sleepMinutes = (int) Duration.between(dayStart, dayFinish).toMinutes();
                String intervalType = isNightSleepArray ? "Ночной сон" : "Дневной сон";
                currentIntervals.add(new SleepWakeIntervalWithTagAndComment(!isNightSleepArray, intervalType,
                        sleepMinutes, dream.getTags(), dream.getComment()));

                isNightSleepArray = !isNightSleepArray;
                previousEndTimeArray = dayFinish;

                intervals.addAll(currentIntervals);

                if (nightStart != null && nightFinish != null) {
                    int nightSleepMinutes = (int) Duration.between(nightStart, nightFinish).toMinutes();
                    String nightIntervalType = isNightSleepArray ? "Ночной сон" : "Дневной сон";
                    intervals.add(new SleepWakeIntervalWithTagAndComment(!isNightSleepArray, nightIntervalType,
                            nightSleepMinutes, dream.getTags(), dream.getComment()));
                }
            }

            if (previousEndTimeArray.isBefore(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))) {
                String lastIntervalType = isNightSleepArray ? "Ночной сон" : "Дневной сон";
                intervals.add(new SleepWakeIntervalWithTagAndComment(
                        isNightSleepArray,
                        lastIntervalType,
                        (int) Duration.between(
                                        previousEndTimeArray,
                                        LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))
                                .toMinutes(),
                        null, null));
            }
        }

        return intervals;
    }
    public List<SleepWakeIntervalWithTagAndComment> calculateSleepWakeIntervalsWithTagsAndComments(LocalDate date) {
        List<Dream> dreams = dreamRepository.findByDate(date);

        List<SleepWakeIntervalWithTagAndComment> intervals = new ArrayList<>();
        LocalDateTime previousEndTimeArray = LocalDateTime.of(date, LocalTime.MIDNIGHT);

        if (dreams.isEmpty()) {
            intervals.add(new SleepWakeIntervalWithTagAndComment(true, "Бодрствование",
                    (int) Duration.between(previousEndTimeArray, LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT)).toMinutes(),
                    null, null));
        } else {
            for (Dream dream : dreams) {
                List<SleepWakeIntervalWithTagAndComment> currentIntervals = new ArrayList<>();
                LocalDateTime dayStart = dream.getDayStart();
                LocalDateTime dayFinish = dream.getDayFinish();
                LocalDateTime nightStart = dream.getNightStart();
                LocalDateTime nightFinish = dream.getNightFinish();

                if (dayStart.isAfter(previousEndTimeArray)) {
                    int awakeMinutes = calculateTotalAwakeMinutes(previousEndTimeArray, dayStart, null, null);
                    intervals.add(new SleepWakeIntervalWithTagAndComment(true, "Бодрствование", awakeMinutes, null, null));
                }

                if (dayStart != null && dayFinish != null) {
                    int sleepMinutes = (int) Duration.between(dayStart, dayFinish).toMinutes();
                    currentIntervals.add(new SleepWakeIntervalWithTagAndComment(false, "Дневной сон",
                            sleepMinutes, dream.getTags(), dream.getComment()));
                }

                if (nightStart != null && nightFinish != null) {
                    int nightSleepMinutes = (int) Duration.between(nightStart, nightFinish).toMinutes();
                    currentIntervals.add(new SleepWakeIntervalWithTagAndComment(true, "Ночной сон",
                            nightSleepMinutes, dream.getTags(), dream.getComment()));
                }

                intervals.addAll(currentIntervals);
                previousEndTimeArray = nightFinish != null ? nightFinish : dayFinish;
            }

            if (previousEndTimeArray.isBefore(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT))) {
                int awakeMinutes = calculateTotalAwakeMinutes(previousEndTimeArray,
                        LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT), null, null);
                intervals.add(new SleepWakeIntervalWithTagAndComment(true, "Бодрствование", awakeMinutes, null, null));
            }
        }

        return intervals;
    }*/


}




