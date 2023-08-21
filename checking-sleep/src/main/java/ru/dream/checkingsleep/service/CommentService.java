package ru.dream.checkingsleep.service;

import ru.dream.checkingsleep.dto.CommentCreateDto;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.dto.CommentUpdateDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Dream;

import java.util.UUID;

public interface CommentService {
    CommentDto getCommentByDream(Dream dream);

    CommentCreateDto createComment(CommentCreateDto commentCreateDto);

    CommentUpdateDto updateComment(CommentUpdateDto commentUpdateDto);

    void deleteComment(UUID id);
}
