package ru.dream.checkingsleep.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.dream.checkingsleep.dto.CommentCreateDto;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.dto.CommentUpdateDto;
import ru.dream.checkingsleep.mappers.basic.BaseMapper;
import ru.dream.checkingsleep.model.Comment;

@Component
@Mapper(config = MappingConfig.class, componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);

    CommentCreateDto toCreateDto(Comment comment);
    Comment toCreateEntity(CommentCreateDto commentCreateDto);

    CommentUpdateDto toUpdateDto(Comment comment);
    Comment toUpdateEntity(CommentUpdateDto commentUpdateDto);
}
