package ru.dream.checkingsleep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SleepWakeInterval {
    private Boolean isSleep;
    private String intervalType;
    private Integer durationMinutes;
}
