package ru.dream.checkingsleep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DreamCreateDto {
    private LocalDateTime dayStart;
    private LocalDateTime dayFinish;
    private LocalDateTime nightStart;
    private LocalDateTime nightFinish;
    private CommentCreateDto comment;
    private TagCreateDto tag;
}
