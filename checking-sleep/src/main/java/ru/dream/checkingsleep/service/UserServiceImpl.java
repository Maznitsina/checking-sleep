package ru.dream.checkingsleep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.UserMapper;
import ru.dream.checkingsleep.model.User;
import ru.dream.checkingsleep.repository.UserRepository;

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
    public UserDto updateUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).orElseThrow();
        UserDto updateDto = userMapper.toDto(user);
        User user1 = userMapper.toEntity(updateDto);
        User user2 = userRepository.save(user1);
        return userMapper.toDto(user2);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
    public MongoPhotoDto getChildPhoto(UUID id, byte[] photo){
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getChildPhotoId());
    }
    public MongoPhotoDto getMomPhoto(UUID id, byte[] photo){
        User user = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getMomPhotoId());
    }
    public MongoPhotoDto getDadPhoto(UUID id, byte[] photo){
        User user = userRepository.findDadPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.getPhotoById(user.getDadPhotoId());
    }
    /*public MongoPhotoDto updateChildPhoto(UUID id, byte[] photo){
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mongoPhotoService.updatePhoto(user.getChildPhotoId());
    }*/
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
  /*  public void createChildPhoto(UUID id, byte[] photo){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        String photoId = mongoPhotoService.createPhoto(photo);
        user.setChildPhotoId(photoId);
        userRepository.save(user);
    }*/



}
