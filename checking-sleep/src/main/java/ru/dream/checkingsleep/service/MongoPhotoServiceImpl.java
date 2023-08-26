package ru.dream.checkingsleep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.mappers.MongoPhotoMapper;
import ru.dream.checkingsleep.model.MongoPhoto;
import ru.dream.checkingsleep.repository.MongoPhotoRepository;


@Service
@RequiredArgsConstructor
public class MongoPhotoServiceImpl implements MongoPhotoService {
    private final MongoPhotoRepository mongoPhotoRepository;
    private final MongoPhotoMapper mongoPhotoMapper;


    public MongoPhotoDto getPhotoById(String id) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }


    public MongoPhotoDto createPhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.save(mongoPhotoMapper.toEntity(mongoPhotoDto));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }

    public MongoPhotoDto updatePhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findPhotoById(mongoPhotoDto.getId()).orElseThrow();
        mongoPhoto.setPhoto(mongoPhotoDto.getPhoto());
        MongoPhoto savedPhoto = mongoPhotoRepository.save(mongoPhoto);
        return mongoPhotoMapper.toDto(savedPhoto);
    }


    public void deletePhoto(String id) {
        mongoPhotoRepository.deleteById(id);
    }
}
