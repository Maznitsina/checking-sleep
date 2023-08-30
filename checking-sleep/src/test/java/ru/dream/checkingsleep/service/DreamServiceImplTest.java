package ru.dream.checkingsleep.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dream.checkingsleep.dto.SleepWakeInterval;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.User;
import ru.dream.checkingsleep.repository.DreamRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DreamServiceImplTest {
    @Mock
    private DreamRepository dreamRepository;
    @InjectMocks
    private DreamServiceImpl dreamService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateSleepWakeIntervalsForDateRange() {
        LocalDate startDate = LocalDate.of(2023,8,12);
        LocalDate endDate = LocalDate.of(2023,8,13);
        Dream dream1 = new Dream();
        dream1.setDayFinish(LocalDateTime.of(2023, 8, 12, 10, 0));
        dream1.setNightStart(LocalDateTime.of(2023, 8, 12, 22, 0));
        dream1.setNightFinish(LocalDateTime.of(2023, 8, 13, 6, 0));
        Dream dream2 = new Dream();
        dream2.setDayStart(LocalDateTime.of(2023, 8, 13, 7, 0));
        dream2.setDayFinish(LocalDateTime.of(2023, 8, 13, 9, 0));
        dream2.setNightStart(LocalDateTime.of(2023, 8, 13, 21, 0));
        dream2.setNightFinish(LocalDateTime.of(2023, 8, 14, 5, 0));
        List<Dream> dreams = Arrays.asList(dream1, dream2);
        when(dreamRepository.findByDate(any())).thenReturn(dreams);
        Map<LocalDate, List<SleepWakeInterval>> intervalsMap = dreamService
                .calculateSleepWakeIntervalsForDateRange(startDate, endDate);
        assertNotNull(intervalsMap);
        assertEquals(2, intervalsMap.size());
        List<SleepWakeInterval> intervalsDay1 = intervalsMap.get(startDate);
        assertNotNull(intervalsDay1);
        List<SleepWakeInterval> intervalsDay2 = intervalsMap.get(endDate);
        assertNotNull(intervalsDay2);

        verify(dreamRepository, times(2)).findByDate(any());




    }
}