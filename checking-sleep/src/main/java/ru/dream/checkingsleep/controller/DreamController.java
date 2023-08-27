package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.service.DreamService;
import ru.dream.checkingsleep.service.DreamServiceImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("dreams")
@SecurityRequirement(name = "dreams-users")
public class DreamController {
    private final DreamService dreamService;

    @PostMapping("/create")
    DreamCreateDto createDream(@RequestBody DreamCreateDto dreamCreateDto) {
        return dreamService.createDream(dreamCreateDto);
    }

    @PutMapping("/update")
    DreamUpdateDto updateDream(@RequestBody DreamUpdateDto dreamUpdateDto) {
        return dreamService.updateDream(dreamUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    void deleteDream(@PathVariable("id") UUID id) {
        dreamService.deleteDream(id);
    }

    @GetMapping("/intervals/date-range/{startDate}/{endDate}")
    public ResponseEntity<Map<LocalDate, List<DreamService.SleepWakeInterval>>> getSleepIntervalsForDateRange(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate) {
        Map<LocalDate, List<DreamServiceImpl.SleepWakeInterval>> intervalsMap = dreamService.calculateSleepWakeIntervalsForDateRange(startDate, endDate);
        return new ResponseEntity<>(intervalsMap, HttpStatus.OK);
    }

}
