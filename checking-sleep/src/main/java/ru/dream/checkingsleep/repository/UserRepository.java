package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.dream.checkingsleep.model.User;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);

    Optional<User> findChildPhotoById(UUID id);
    Optional<User> findMomPhotoById(UUID id);
    Optional<User> findDadPhotoById(UUID id);




}
