package it.MSUsers.MSUsers.controller;

import it.MSUsers.MSUsers.dto.LoginDTO;
import it.MSUsers.MSUsers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/V1/public/account/login")
public class LoginController {
    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<String> loginAccountController(@RequestBody LoginDTO userLogin, BindingResult result) {
        boolean loginStatus = accountService.loginAccount(userLogin, result);
        if (loginStatus) {
            return ResponseEntity.status(HttpStatus.OK).body("Authenticated");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Authenticated");
        }
    }
}
