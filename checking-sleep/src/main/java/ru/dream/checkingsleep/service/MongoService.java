package ru.dream.checkingsleep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dream.checkingsleep.model.MongoPhoto;
import ru.dream.checkingsleep.repository.MongoPhotoRepository;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    MongoPhotoRepository mongoPhotoRepository;

    public List<MongoPhoto> findAll() {
        return mongoPhotoRepository.findAll();
    }
    public MongoPhoto save(MongoPhoto mongoPhoto) {
        return mongoPhotoRepository.save(mongoPhoto);
    }
}
