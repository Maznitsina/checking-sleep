package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.DreamCreateDto;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.dto.DreamUpdateDto;
import ru.dream.checkingsleep.model.Dream;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring", uses = {UserMapper.class, TagMapper.class, CommentMapper.class})
public interface DreamMapper {

    DreamDto toDto(Dream dream);

    Dream toEntity(DreamDto dreamDto);
    DreamCreateDto toCreateDto(Dream dream);
    Dream toCreateEntity(DreamCreateDto dreamDto);

    DreamUpdateDto toUpdateDto(Dream dream);
    Dream toUpdateEntity(DreamUpdateDto dreamUpdateDto);

    void updateDreamFromDto(DreamUpdateDto dreamUpdateDto, @MappingTarget Dream dream);


}
