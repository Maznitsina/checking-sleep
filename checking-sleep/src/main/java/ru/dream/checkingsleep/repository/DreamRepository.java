package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.dream.checkingsleep.model.Dream;
import ru.dream.checkingsleep.model.User;

public interface DreamRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<Dream> {

    Dream findFinishById(Long id);
}
