package it.MSUsers.MSUsers.service;

import it.MSUsers.MSUsers.DAO.UserRegisterDAO;
import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.entity.UserEntity;
import it.MSUsers.MSUsers.exception.DataValidationException;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.UserRepository;
import it.MSUsers.MSUsers.utils.HandelValidationError;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;


    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) return users;
        else return users;
    }

    @Transactional(readOnly = true)
    public UserEntity getUserUsingId(long id) {
        return userRepository.getUserById(id).
                orElseThrow(() -> new NotFoundException("User not found with that id (%d)".
                        formatted(id)));
    }



    public Boolean createUser(@Valid UserRegisterDAO new_user_account, BindingResult result) {
        if (result.hasErrors()) {
            throw new DataValidationException(HandelValidationError.getAllErrors(result));
        } else {
            // create first the user
            System.out.println("Object is valid and ready to be saved");
            UserEntity new_user = new UserEntity();
            new_user.setFirstName(new_user_account.getFirstName());
            new_user.setLastName(new_user_account.getLastName());
            new_user.setBirthDate(LocalDate.parse(new_user_account.getBirthDate()));
            new_user.setUserRole(roleService.findRoleSet(1));

            // save the new user
            try {
                UserEntity createdUser = userRepository.save(new_user);
                System.out.println("User created");
                AccountEntity new_account = new AccountEntity();
                // Check if the username and email is not repeated
                if (!accountService.getAccountUsername(new_user_account.getUsername()).isEmpty()) {
                    throw new DataValidationException("Username entered (%s) already exists".formatted(new_user_account.getUsername()));
                } else if (!accountService.getAccountEmail(new_user_account.getEmail()).isEmpty()) {
                    throw new DataValidationException("Email entered (%s) already exists".formatted(new_user_account.getEmail()));
                } else {
                    new_account.setUsername(new_user_account.getUsername());
                    new_account.setEmail(new_user_account.getEmail());
                    new_account.setPassword(new_user_account.getPassword());
                    new_account.setUser(createdUser);
                    accountService.createAccountFromUser(new_account);
                    System.out.println("Account created");
                }
                return true;
            } catch (Exception e) {
                return false;
            }

        }
    }


}
