package it.MSUsers.MSUsers.repository;

import it.MSUsers.MSUsers.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
