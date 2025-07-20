package it.MSUsers.MSUsers.service;

import io.swagger.v3.core.util.Json;
import it.MSUsers.MSUsers.dto.LoginDTO;
import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.exception.DataValidationException;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.AccountRepository;
import it.MSUsers.MSUsers.utils.HandelValidationError;
import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.*;

@Service

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional
    public Boolean createAccountFromUser(AccountEntity new_account) {
        try {
            // convert the password to hash
            new_account.setPassword(passwordEncoder.encode(new_account.getPassword()));
            accountRepository.save(new_account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Optional<AccountEntity> getAccountUsername(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    @Transactional
    public Optional<AccountEntity> getAccountEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }

    @Transactional
    public Boolean loginAccount(LoginDTO login_obj, BindingResult result) {
        System.out.println("login service called");
        if (result.hasErrors()) {
            throw new DataValidationException(HandelValidationError.getAllErrors(result));
        } else {
            // get the account
            AccountEntity account = accountRepository.getAccountByUsername(login_obj.getUsername()).orElseThrow(() -> new NotFoundException("Account not found with this username (%s)".formatted(login_obj.getUsername())));
            if(checkPassword(login_obj.getPassword(), account.getPassword())) {
                if(!account.getAccountLoginCurrentStatus()) {
                    account.setAccountLoginCurrentStatus(true);
                    System.out.println("Account current login status changes but not saved yet");
                    JSONObject loginDetail = new JSONObject();
                    loginDetail.put(account.getId() + "-" + LocalDateTime.now().toString(), true);
                    System.out.println("The json for login detail added: " + loginDetail);
                    account.setLogInDetails(loginDetail.toJSONString());
                    accountRepository.save(account);
                    System.out.println("Account current login status changes and saved");
                    return true;
                } else{
                    throw new DataValidationException("This user (%s) is already log in".formatted(account.getUsername()));
                }

            } else {
                return false;
            }

        }
    }


    // Example for verifying a password during login
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }




}
