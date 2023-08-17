package ru.dream.checkingsleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.checkingsleep.model.ERole;
import ru.dream.checkingsleep.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
