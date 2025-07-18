package it.MSUsers.MSUsers.controller;


import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/V1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAllAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable  Long id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(id));
    }

}
