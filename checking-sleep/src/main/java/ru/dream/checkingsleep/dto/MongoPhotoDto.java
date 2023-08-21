package ru.dream.checkingsleep.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MongoPhotoDto {

    private String id;

    private String childPhoto;

    private String momPhoto;

    private String dadPhoto;
}
