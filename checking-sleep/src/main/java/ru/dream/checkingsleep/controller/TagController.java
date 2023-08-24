package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.TagService;

import java.util.UUID;

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
    TagCreateDto createTag(@RequestBody TagCreateDto tagCreateDto) {
        return tagService.createTag(tagCreateDto);
    }

    @PutMapping("/tag/update")
    TagUpdateDto updateTag(@RequestBody TagUpdateDto tagUpdateDto) {
        return tagService.updateTag(tagUpdateDto);
    }

    @DeleteMapping("/tag/delete/{id}")
    void deleteTag(@PathVariable("id") UUID id) {
        tagService.deleteTag(id);
    }

}
