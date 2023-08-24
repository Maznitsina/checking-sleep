package ru.dream.checkingsleep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.MongoPhotoMapper;
import ru.dream.checkingsleep.model.MongoPhoto;
import ru.dream.checkingsleep.repository.MongoPhotoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MongoPhotoServiceImpl implements MongoPhotoService {
    private final MongoPhotoRepository mongoPhotoRepository;
    private final MongoPhotoMapper mongoPhotoMapper;
    private final UserService userService;


    public MongoPhotoDto getChildPhotoById(String id) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }

    public MongoPhotoDto getMomPhotoById(String id) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }

    public MongoPhotoDto getDadPhotoById(String id) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }

    public MongoPhotoDto createPhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.save(mongoPhotoMapper.toEntity(mongoPhotoDto));
        return mongoPhotoMapper.toDto(mongoPhoto);
    }

    public MongoPhotoDto updateChildPhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findChildPhotoById(mongoPhotoDto.getId()).orElseThrow();
        mongoPhoto.setChildPhoto(mongoPhotoDto.getChildPhoto());
        MongoPhoto savedChildPhoto = mongoPhotoRepository.save(mongoPhoto);
        return mongoPhotoMapper.toDto(savedChildPhoto);
    }

    public MongoPhotoDto updateMomPhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findMomPhotoById(mongoPhotoDto.getId()).orElseThrow();
        mongoPhoto.setMomPhoto(mongoPhotoDto.getMomPhoto());
        MongoPhoto savedMomPhoto = mongoPhotoRepository.save(mongoPhoto);
        return mongoPhotoMapper.toDto(savedMomPhoto);
    }

    public MongoPhotoDto updateDadPhoto(MongoPhotoDto mongoPhotoDto) {
        MongoPhoto mongoPhoto = mongoPhotoRepository.findDadPhotoById(mongoPhotoDto.getId()).orElseThrow();
        mongoPhoto.setDadPhoto(mongoPhotoDto.getDadPhoto());
        MongoPhoto savedDadPhoto = mongoPhotoRepository.save(mongoPhoto);
        return mongoPhotoMapper.toDto(savedDadPhoto);
    }

    public void deletePhoto(String id) {
        mongoPhotoRepository.deleteById(id);
    }
}
