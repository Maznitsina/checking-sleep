package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.mappers.basic.BaseMapper;
import ru.dream.checkingsleep.model.Tag;


@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDto, Tag> {

    TagDto toCreateTag

}
