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

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class DreamController {
    private final DreamService dreamService;
    @GetMapping("dream/{id}")
    DreamDto getDreamById(@PathVariable("id") UUID id) {
        return dreamService.getDreamById(id);
    }
    @GetMapping("dream/all/{id}")
    List<DreamDto> getAllDreams(@PathVariable("id") UUID id){
        return dreamService.getAllDreams();
    }
    @PostMapping("/dream/create")
    DreamCreateDto createDream(@RequestBody DreamCreateDto dreamCreateDto) {
        return dreamService.createDream(dreamCreateDto);
    }

    @PutMapping("/dream/update")
    DreamUpdateDto updateDream(@RequestBody DreamUpdateDto dreamUpdateDto) {
        return dreamService.updateDream(dreamUpdateDto);
    }

    @DeleteMapping("/dream/delete/{id}")
    void deleteDream(@PathVariable("id") UUID id) {
        dreamService.deleteDream(id);
    }
}
