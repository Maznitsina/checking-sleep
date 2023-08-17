package ru.dream.checkingsleep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dream.checkingsleep.model.Comment;
import ru.dream.checkingsleep.model.Tag;
import ru.dream.checkingsleep.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DreamDto {
    private UUID id;
    private UserDto user;
    private LocalDateTime dayStart;
    private LocalDateTime dayFinish;
    private LocalDateTime nightStart;
    private LocalDateTime nightFinish;
    private CommentDto comment;
    private TagDto tag;

}
