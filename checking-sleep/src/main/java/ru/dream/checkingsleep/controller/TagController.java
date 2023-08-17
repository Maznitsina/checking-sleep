package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.TagCreateDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.TagService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class TagController {
    private final TagService tagService;

    @GetMapping("/tag/{dream}")
    TagDto getTagByDream(@PathVariable("dream") Dream dream) {

        return tagService.getTagByDream(dream);
    }
    @PostMapping("/tag/create")
    TagDto createTag(@RequestBody TagCreateDto tagCreateDto) {
        return tagService.createTag(tagCreateDto);
    }


}
