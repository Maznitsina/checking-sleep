package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.*;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
@SecurityRequirement(name = "dreams-users")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{dream}")
    CommentDto getCommentByDream(@PathVariable("dream") Dream dream) {

        return commentService.getCommentByDream(dream);
    }
    @PostMapping("/create")
    CommentCreateDto createComment(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.createComment(commentCreateDto);
    }

    @PutMapping("/update")
    CommentUpdateDto updateComment(@RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment(commentUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteComment(@PathVariable("id") UUID id) {
        commentService.deleteComment(id);
    }
}
