package ru.dream.checkingsleep.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.dream.checkingsleep.model.MongoPhoto;
import java.util.Optional;

@EnableMongoRepositories
public interface MongoPhotoRepository extends MongoRepository<MongoPhoto, String> {

    Optional<MongoPhoto> findById(String id);
    Optional<MongoPhoto> findPhotoById(String id);


}
