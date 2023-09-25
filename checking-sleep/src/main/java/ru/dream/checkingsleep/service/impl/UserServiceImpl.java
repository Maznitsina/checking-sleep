package ru.dream.checkingsleep.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.UserMapper;
import ru.dream.checkingsleep.model.User;
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow();
        UserDto updateDto = userMapper.toDto(user);
        User user1 = userMapper.toEntity(updateDto);
        User user2 = userRepository.save(user1);
        return userMapper.toDto(user2);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public MongoPhotoDto getChildPhoto(UUID id) {
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getChildPhotoId());
    }

    public MongoPhotoDto getMomPhoto(UUID id) {
        User user = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getMomPhotoId());
    }

    public MongoPhotoDto getDadPhoto(UUID id) {
        User user = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getDadPhotoId());
    }

    public MongoPhotoDto updateChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(user.getChildPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public MongoPhotoDto updateMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(user.getMomPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public MongoPhotoDto updateDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        MongoPhotoDto currentPhoto = mongoPhotoService.getPhotoById(user.getDadPhotoId());
        return mongoPhotoService.updatePhoto(currentPhoto);
    }

    public void deleteChildPhoto(UUID id) {
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(user.getChildPhotoId());
    }

    public void deleteMomPhoto(UUID id) {
        User user = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(user.getMomPhotoId());
    }

    public void deleteDadPhoto(UUID id) {
        User user = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        mongoPhotoService.deletePhoto(user.getDadPhotoId());
    }

    public void createChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        user.setChildPhotoId(String.valueOf(photoId));
        userRepository.save(user);
    }

    public void createMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        user.setMomPhotoId(String.valueOf(photoId));
        userRepository.save(user);
    }

    public void createDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        MongoPhotoDto photoId = mongoPhotoService.createPhoto(mongoPhotoDto);
        user.setDadPhotoId(String.valueOf(photoId));
        userRepository.save(user);
    }

}
