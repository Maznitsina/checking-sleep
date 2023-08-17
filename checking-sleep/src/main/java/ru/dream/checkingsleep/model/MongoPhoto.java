package ru.dream.checkingsleep.model;

import jakarta.persistence.Column;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Photo")
public class MongoPhoto {
    @Id
    private String id;

    @Field(name = "childPhoto")
    private String childPhoto;

    @Field(name = "momPhoto")
    private String momPhoto;

    @Field(name = "dadPhoto")
    private String dadPhoto;
}
