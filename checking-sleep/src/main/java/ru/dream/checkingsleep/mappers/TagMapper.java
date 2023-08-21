package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.TagCreateDto;
import ru.dream.checkingsleep.dto.TagDto;

import ru.dream.checkingsleep.dto.TagUpdateDto;
import ru.dream.checkingsleep.model.Tag;


@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tagDto);

    TagCreateDto toCreateDto(Tag tag);
    Tag toCreateEntity(TagCreateDto tagCreateDto);

    TagUpdateDto toUpdateDto(Tag tag);
    Tag toUpdateEntity(TagUpdateDto tagUpdateDto);





}
