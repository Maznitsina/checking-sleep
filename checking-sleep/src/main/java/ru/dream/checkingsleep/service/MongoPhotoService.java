package ru.dream.checkingsleep.service;

import ru.dream.checkingsleep.dto.MongoPhotoDto;


public interface MongoPhotoService {
    MongoPhotoDto getPhotoById(String id);

    MongoPhotoDto createPhoto(MongoPhotoDto mongoPhotoDto);

    MongoPhotoDto updatePhoto(MongoPhotoDto mongoPhotoDto);

    void deletePhoto(String id);

}
