package ru.dream.checkingsleep.service;

import ru.dream.checkingsleep.dto.MongoPhotoDto;


public interface MongoPhotoService {
    MongoPhotoDto getChildPhotoById(String id);

    MongoPhotoDto getDadPhotoById(String id);

    MongoPhotoDto getMomPhotoById(String id);

    MongoPhotoDto createPhoto(MongoPhotoDto mongoPhotoDto);

    MongoPhotoDto updateChildPhoto(MongoPhotoDto mongoPhotoDto);

    MongoPhotoDto updateMomPhoto(MongoPhotoDto mongoPhotoDto);

    MongoPhotoDto updateDadPhoto(MongoPhotoDto mongoPhotoDto);
    void deletePhoto(String id);

}
