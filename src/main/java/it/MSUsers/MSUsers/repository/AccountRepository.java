package it.MSUsers.MSUsers.repository;

import it.MSUsers.MSUsers.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
