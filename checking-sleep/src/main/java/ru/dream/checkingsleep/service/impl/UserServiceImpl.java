package ru.dream.checkingsleep.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.UserMapper;
import ru.dream.checkingsleep.model.UserInfo;
import ru.dream.checkingsleep.repository.UserRepository;
import ru.dream.checkingsleep.service.MongoPhotoService;
import ru.dream.checkingsleep.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final MongoPhotoService mongoPhotoService;

    public UserDto getUserById(UUID id) {
        UserInfo userInfo = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(userInfo);
    }

    public UserDto createUser(UserDto userDto) {
        UserInfo userInfo = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(userInfo);
    }

    public UserDto updateUser(UserDto userDto) {
        UserInfo userInfo = userRepository.findById(userDto.getId()).orElseThrow();
        UserDto updateDto = userMapper.toDto(userInfo);
        UserInfo userInfo1 = userMapper.toEntity(updateDto);
        UserInfo userInfo2 = userRepository.save(userInfo1);
        return userMapper.toDto(userInfo2);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public MongoPhotoDto getChildPhoto(UUID id) {
        UserInfo userInfo = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(userInfo.getChildPhotoId());
    }

    public MongoPhotoDto getMomPhoto(UUID id) {
        UserInfo userInfo = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(userInfo.getMomPhotoId());
    }

    public MongoPhotoDto getDadPhoto(UUID id) {
        UserInfo userInfo = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(userInfo.getDadPhotoId());
    }

    public MongoPhotoDto updateChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(userInfo.getChildPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public MongoPhotoDto updateMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(userInfo.getMomPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public MongoPhotoDto updateDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(userInfo.getDadPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public void deleteChildPhoto(UUID id) {
        UserInfo userInfo = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(userInfo.getChildPhotoId());
    }

    public void deleteMomPhoto(UUID id) {
        UserInfo userInfo = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(userInfo.getMomPhotoId());
    }

    public void deleteDadPhoto(UUID id) {
        UserInfo userInfo = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(userInfo.getDadPhotoId());
    }

    public void createChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        userInfo.setChildPhotoId(String.valueOf(photoId));
        userRepository.save(userInfo);
    }

    public void createMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        userInfo.setMomPhotoId(String.valueOf(photoId));
        userRepository.save(userInfo);
    }

    public void createDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        UserInfo userInfo = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        userInfo.setDadPhotoId(String.valueOf(photoId));
        userRepository.save(userInfo);
    }

}
