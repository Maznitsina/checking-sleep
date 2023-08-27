package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;

import java.util.UUID;

@Service
public interface UserService {
    public UserDto getUserById (UUID id);
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    void deleteUser(UUID id);
    public MongoPhotoDto getChildPhoto(UUID id, byte[] photo);
    public  MongoPhotoDto getMomPhoto(UUID id, byte[] photo);
    public MongoPhotoDto getDadPhoto(UUID id, byte[] photo);
    //public MongoPhotoDto updateChildPhoto(UUID id, byte[] photo);
    void deleteChildPhoto(UUID id);
    void deleteMomPhoto(UUID id);
    void deleteDadPhoto(UUID id);
}
