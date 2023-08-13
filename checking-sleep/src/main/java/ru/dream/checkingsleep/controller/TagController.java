package ru.dream.checkingsleep.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.TagService;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/tag/{id}")
    TagDto getTagByDream(@PathVariable("dream") Dream dream) {

        return tagService.getTagByDream(dream.getTags());
    }


}
