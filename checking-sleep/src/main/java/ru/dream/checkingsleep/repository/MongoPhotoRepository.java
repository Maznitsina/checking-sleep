package ru.dream.checkingsleep.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dream.checkingsleep.model.MongoPhoto;

import java.util.UUID;

public interface MongoPhotoRepository extends MongoRepository<MongoPhoto, UUID> {
}
