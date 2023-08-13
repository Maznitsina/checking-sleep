package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dream.checkingsleep.dto.DreamDto;
import ru.dream.checkingsleep.model.Dream;

@Mapper(componentModel = "spring")
public interface DreamMapper {
    DreamMapper INSTANCE = Mappers.getMapper(DreamMapper.class);

    DreamDto dreamToDto(Dream dream);
}
