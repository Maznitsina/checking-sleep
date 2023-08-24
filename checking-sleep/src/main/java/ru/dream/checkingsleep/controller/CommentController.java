package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{dream}")
    CommentDto getCommentByDream(@PathVariable("dream") Dream dream) {

        return commentService.getCommentByDream(dream);
    }
    @PostMapping("/comment/create")
    CommentCreateDto createComment(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.createComment(commentCreateDto);
    }

    @PutMapping("/comment/update")
    CommentUpdateDto updateComment(@RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment(commentUpdateDto);
    }

    @DeleteMapping("/comment/delete/{id}")
    void deleteComment(@PathVariable("id") UUID id) {
        commentService.deleteComment(id);
    }
}
