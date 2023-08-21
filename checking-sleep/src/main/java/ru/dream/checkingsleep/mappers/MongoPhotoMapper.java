package ru.dream.checkingsleep.mappers;

import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.model.MongoPhoto;

public interface MongoPhotoMapper {
    MongoPhotoDto toDto(MongoPhoto mongoPhoto);
    MongoPhoto toEntity(MongoPhotoDto mongoPhotoDto);
}
