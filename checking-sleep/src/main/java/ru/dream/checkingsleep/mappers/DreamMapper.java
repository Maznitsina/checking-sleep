package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.nodes.Tag;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.mappers.basic.BaseMapper;
import ru.dream.checkingsleep.model.Dream;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring", uses = {UserMapper.class, TagMapper.class, CommentMapper.class})
public interface DreamMapper {
    @Mapping(source = "tagDto", target = "tag")
    @Mapping(source = "commentDto", target = "comment")
    DreamDto toDto(Dream dream);

    @Mapping(target = "tag", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comment", ignore = true)
    Dream toEntity(DreamDto dreamDto);


}
