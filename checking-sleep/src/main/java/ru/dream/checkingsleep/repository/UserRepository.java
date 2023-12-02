package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.dream.checkingsleep.model.UserInfo;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findById(UUID id);

    Optional<UserInfo> findChildPhotoById(UUID id);
    Optional<UserInfo> findMomPhotoById(UUID id);
    Optional<UserInfo> findDadPhotoById(UUID id);




}
