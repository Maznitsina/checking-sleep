package ru.dream.checkingsleep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dream.checkingsleep.model.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DreamUpdateDto {
    private UUID id;
    private LocalDateTime dayStart;
    private LocalDateTime dayFinish;
    private LocalDateTime nightStart;
    private LocalDateTime nightFinish;
    private CommentUpdateDto comment;
    private TagUpdateDto tag;

}
