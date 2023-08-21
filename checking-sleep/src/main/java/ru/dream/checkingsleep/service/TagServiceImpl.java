package ru.dream.checkingsleep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.mappers.TagMapper;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.Tag;
import ru.dream.checkingsleep.repository.TagRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagDto getTagByDream(Dream dream) {
        Tag tag = tagRepository.findByDream(dream)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return tagMapper.toDto(tag);
    }

    @Override
    public TagCreateDto createTag(TagCreateDto tagCreateDto) {
        Tag tag = tagRepository.save(tagMapper.toCreateEntity(tagCreateDto));
        return tagMapper.toCreateDto(tag);
    }

    @Override
    public TagUpdateDto updateTag(TagUpdateDto tagUpdateDto) {
        Tag tag = tagRepository.findById(tagUpdateDto.getId()).orElseThrow();
        tag.setTag(tagUpdateDto.getTag());
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toUpdateDto(savedTag);
    }

    public void deleteTag(UUID id) {
        tagRepository.deleteById(id);
    }
}
