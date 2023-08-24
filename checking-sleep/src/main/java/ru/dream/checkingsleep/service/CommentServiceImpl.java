package ru.dream.checkingsleep.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.dto.CommentCreateDto;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.dto.CommentUpdateDto;
import ru.dream.checkingsleep.mappers.CommentMapper;
import ru.dream.checkingsleep.model.Comment;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.repository.CommentRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto getCommentByDream(Dream dream) {
        Comment comment = commentRepository.findByDream(dream)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        return commentMapper.toDto(comment);

    }

    @Override
    public CommentCreateDto createComment(CommentCreateDto commentCreateDto) {
        Comment comment = commentRepository.save(commentMapper.toCreateEntity(commentCreateDto));
        return commentMapper.toCreateDto(comment);
    }

    @Override
    public CommentUpdateDto updateComment(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentUpdateDto.getId()).orElseThrow();
        CommentUpdateDto updateComment = commentMapper.toUpdateDto(comment);
        Comment comment1 = commentMapper.toUpdateEntity(updateComment);
        Comment comment2 = commentRepository.save(comment1);
//        comment.setComment(commentUpdateDto.getComment());
//        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toUpdateDto(comment2);
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
