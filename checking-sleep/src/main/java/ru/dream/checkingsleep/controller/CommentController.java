package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.dream.checkingsleep.dto.CommentDto;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.service.CommentService;
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{id}")
    CommentDto getCommentByDream(@PathVariable("dream") Dream dream) {

        return commentService.getCommentByDream(dream);
    }


}
