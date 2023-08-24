package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.model.MongoPhoto;
@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface MongoPhotoMapper {
    MongoPhotoDto toDto(MongoPhoto mongoPhoto);
    MongoPhoto toEntity(MongoPhotoDto mongoPhotoDto);
}
