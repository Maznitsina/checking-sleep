package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.TagCreateDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.dto.TagUpdateDto;
import ru.dream.checkingsleep.model.Dream;

import java.util.UUID;

@Service
public interface TagService {

    TagDto getTagByDream(Dream dream);

    TagCreateDto createTag(TagCreateDto tagCreateDto);

    TagUpdateDto updateTag(TagUpdateDto tagUpdateDto);

    void deleteTag(UUID id);
}
