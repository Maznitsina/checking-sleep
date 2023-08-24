package ru.dream.checkingsleep.service;

import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.UserDto;

import java.util.UUID;

@Service
public interface UserService {
    public UserDto getUserById (UUID id);
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    void deleteUser(UUID id);
    public UserDto getChildPhoto(UUID id);
    public UserDto getMomPhoto(UUID id);
    public UserDto getDadPhoto(UUID id);
}
