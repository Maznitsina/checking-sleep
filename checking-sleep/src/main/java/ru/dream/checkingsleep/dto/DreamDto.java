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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DreamDto {
    private Long id;
    private User user;
    private LocalDateTime start;
    private LocalDateTime finish;

}
