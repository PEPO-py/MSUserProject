package it.MSUsers.MSUsers.repository;

import it.MSUsers.MSUsers.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getUserById(Long id);
}
