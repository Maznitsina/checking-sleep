package ru.dream.checkingsleep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SleepWakeIntervalWithTagAndComment {
    private boolean isSleep;
    private String type;
    private int duration;
    private List<String> tags;
    private String comment;
}
