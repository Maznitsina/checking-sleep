package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamFilter;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.service.DreamService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class DreamController {
    private final DreamService dreamService;
    private final DreamFilter dreamFilter;

    @GetMapping("dream/{id}")
    Page<DreamDto> getDreambyUser(@PathVariable DreamFilter filter) {
        return dreamService.getDreamByUser(dreamFilter.getUser());
    }
    @PostMapping("/dream/create")
    DreamDto createDream(@RequestBody DreamCreateDto dreamCreateDto) {
        return dreamService.createDream(dreamCreateDto);
    }

    @PutMapping("/dream/update")
    DreamDto updateDream(@RequestBody DreamUpdateDto dreamUpdateDto) {
        return dreamService.updateDream(dreamUpdateDto);
    }

    @DeleteMapping("/dream/delete/{id}")
    void deleteDream(@PathVariable("id") UUID id) {
        dreamService.deleteDream(id);
    }

}
