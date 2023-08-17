package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.mappers.basic.BaseMapper;
import ru.dream.checkingsleep.model.User;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
