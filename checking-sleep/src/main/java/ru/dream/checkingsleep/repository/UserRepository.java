package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.dream.checkingsleep.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
    Boolean existsByMail(String mail);


}
