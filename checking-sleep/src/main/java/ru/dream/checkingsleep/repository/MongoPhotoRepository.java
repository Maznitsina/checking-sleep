package ru.dream.checkingsleep.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.dream.checkingsleep.model.MongoPhoto;
import java.util.Optional;

@EnableMongoRepositories
public interface MongoPhotoRepository extends MongoRepository<MongoPhoto, String> {

    Optional<MongoPhoto> findById(String id);
    Optional<MongoPhoto> findChildPhotoById(String id);
    Optional<MongoPhoto> findDadPhotoById(String id);
    Optional<MongoPhoto> findMomPhotoById(String id);

}
