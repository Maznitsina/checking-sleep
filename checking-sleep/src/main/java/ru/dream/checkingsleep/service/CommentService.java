package ru.dream.checkingsleep.service;

import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.dto.TagDto;
import ru.dream.checkingsleep.model.Dream;

public interface CommentService {
    CommentDto getCommentByDream(Dream dream);
}
