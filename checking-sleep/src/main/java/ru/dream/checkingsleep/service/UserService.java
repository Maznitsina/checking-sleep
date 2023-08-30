package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;

import java.util.UUID;

@Service
public interface UserService {
    public UserDto getUserById(UUID id);

    public UserDto createUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto);

    void deleteUser(UUID id);

    public MongoPhotoDto getChildPhoto(UUID id);

    public MongoPhotoDto getMomPhoto(UUID id);

    public MongoPhotoDto getDadPhoto(UUID id);

    public MongoPhotoDto updateChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto);
    public MongoPhotoDto updateDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto);
    public MongoPhotoDto updateMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto);

    void deleteChildPhoto(UUID id);

    void deleteMomPhoto(UUID id);

    void deleteDadPhoto(UUID id);

    void createChildPhoto(UUID id, MongoPhotoDto mongoPhotoDto);
    void createMomPhoto(UUID id, MongoPhotoDto mongoPhotoDto);
    void createDadPhoto(UUID id, MongoPhotoDto mongoPhotoDto);
}
