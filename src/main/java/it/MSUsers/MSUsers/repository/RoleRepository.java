package it.MSUsers.MSUsers.repository;

import it.MSUsers.MSUsers.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> getRoleById(long id);
}
