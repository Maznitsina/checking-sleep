package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Tag;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto tagToDto(Tag tag);
}
