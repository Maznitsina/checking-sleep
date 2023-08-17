package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.mappers.basic.BaseMapper;
import ru.dream.checkingsleep.model.Comment;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface CommentMapper extends BaseMapper<CommentDto, Comment> {
}
