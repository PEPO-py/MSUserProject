package it.MSUsers.MSUsers.repository;

import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> getAccountById(long id);

    Optional<AccountEntity> getAccountByUsername(String username);
    Optional<AccountEntity> getAccountByEmail(String email);
}
