package ru.dream.checkingsleep.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.service.DreamService;

@RestController
@RequiredArgsConstructor
public class DreamController {
    private final DreamService dreamService;

    @GetMapping("/dream/{id}")
    DreamDto getDreamById(@PathVariable("id") Long id) {

        return dreamService.getDreamById(id);
    }


}
