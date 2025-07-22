package it.MSUsers.MSUsers.service;

import it.MSUsers.MSUsers.DAO.UserRegisterDAO;
import it.MSUsers.MSUsers.dto.StudenteCorsiDTO;
import it.MSUsers.MSUsers.entity.AccountEntity;
import it.MSUsers.MSUsers.entity.UserEntity;
import it.MSUsers.MSUsers.exception.DataValidationException;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.UserRepository;
import it.MSUsers.MSUsers.utils.HandelValidationError;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    public UserService(RestTemplateBuilder builder, @Value("${msitsa.api.url}") String apiUrl) {
        this.restTemplate = builder.build();
        this.apiBaseUrl = apiUrl;
    }


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
            UserEntity new_user = new UserEntity();
            new_user.setFirstName(new_user_account.getFirstName());
            new_user.setLastName(new_user_account.getLastName());
            new_user.setBirthDate(LocalDate.parse(new_user_account.getBirthDate()));
            new_user.setUserRole(roleService.findRoleSet(1));

            // save the new user
            try {
                UserEntity createdUser = userRepository.save(new_user);
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
                }
                return true;
            } catch (Exception e) {
                return false;
            }

        }
    }

    @Transactional
    public StudenteCorsiDTO getCorsiOfStudenti(long studente_id) {
        String url = this.apiBaseUrl + "/studente/" + studente_id;
        return restTemplate.getForObject(url, StudenteCorsiDTO.class);
    }


}
