package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.model.UserInfo;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring", uses = MongoPhotoMapper.class)
public interface UserMapper {
    UserDto toDto(UserInfo userInfo);
    UserInfo toEntity(UserDto userDto);
}
