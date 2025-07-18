package it.MSUsers.MSUsers.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.v3.core.util.Json;
import it.MSUsers.MSUsers.DAO.UserRegisterDAO;
import it.MSUsers.MSUsers.entity.UserEntity;
import it.MSUsers.MSUsers.exception.DataValidationException;
import it.MSUsers.MSUsers.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userService.getUserUsingId(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createNewUser(@Valid @RequestBody UserRegisterDAO createUserAccount, BindingResult result) {
        if(userService.createUser(createUserAccount, result))return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        else return new ResponseEntity<>("User not created", HttpStatus.NOT_IMPLEMENTED);
    }

}
