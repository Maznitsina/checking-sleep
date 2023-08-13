package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.model.Comment;


@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto commentToDto(Comment comment);
}
