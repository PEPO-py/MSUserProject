package it.MSUsers.MSUsers.service;

import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<AccountEntity> getAllAccount(){
        List<AccountEntity> allAccount = accountRepository.findAll();

        if(allAccount.isEmpty()) {
            throw new NotFoundException("Accounts not found");
        } else return allAccount;
    }

    @Transactional(readOnly = true)
    public AccountEntity getAccountById(Long id){
        return accountRepository.getAccountById(id).orElseThrow(() -> new NotFoundException("Account not found with tnis id (%d) ".formatted(id)));
    }



}
