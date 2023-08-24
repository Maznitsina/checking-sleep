package ru.dream.checkingsleep.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.MongoPhotoMapper;
import ru.dream.checkingsleep.mappers.UserMapper;
import ru.dream.checkingsleep.model.MongoPhoto;
import ru.dream.checkingsleep.model.User;
import ru.dream.checkingsleep.repository.MongoPhotoRepository;
import ru.dream.checkingsleep.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }
    public UserDto getChildPhoto(UUID id) {
        User user = userRepository.findChildPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }
    public UserDto getMomPhoto(UUID id) {
        User user = userRepository.findMomPhotoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return userMapper.toDto(user);
    }
    public UserDto getDadPhoto(UUID id) {
        User user = userRepository.findDadPhotoById(id)
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

}
