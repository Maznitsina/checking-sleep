package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.TagService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("tag")
@SecurityRequirement(name = "dreams-users")
public class TagController {
    private final TagService tagService;

    @GetMapping("/{dream}")
    TagDto getTagByDream(@RequestParam("dream") Dream dream) {

        return tagService.getTagByDream(dream);
    }
    @PostMapping("/create")
    TagCreateDto createTag(@RequestBody TagCreateDto tagCreateDto) {
        return tagService.createTag(tagCreateDto);
    }

    @PutMapping("/update")
    TagUpdateDto updateTag(@RequestBody TagUpdateDto tagUpdateDto) {
        return tagService.updateTag(tagUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTag(@RequestParam("id") UUID id) {
        tagService.deleteTag(id);
    }

}
