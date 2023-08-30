package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.dto.SleepWakeInterval;
import ru.dream.checkingsleep.service.DreamService;



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
    Map<LocalDate, List<SleepWakeInterval>> calculateSleepWakeIntervalsForDateRange(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return dreamService.calculateSleepWakeIntervalsForDateRange(startDate, endDate);
    }

}
