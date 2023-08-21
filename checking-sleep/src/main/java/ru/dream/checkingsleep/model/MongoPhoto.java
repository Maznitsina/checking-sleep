package ru.dream.checkingsleep.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Photo")
public class MongoPhoto {
    @Id
    private String id;

    @Indexed(name = "childPhoto")
    private String childPhoto;

    @Indexed(name = "momPhoto")
    private String momPhoto;

    @Indexed(name = "dadPhoto")
    private String dadPhoto;
}
