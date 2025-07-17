package it.MSUsers.MSUsers.service;

import it.MSUsers.MSUsers.entity.RoleEntity;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.RoleRepository;
import it.MSUsers.MSUsers.utils.HandelValidationError;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // This treats the query second to the ACID concepts
    // readOnly=true because this get the db object and not modify any thing
    @Transactional(readOnly = true)
    public List<RoleEntity> getAllRoles() {
        // This will return a list of roles db objects
        List<RoleEntity> allRoles = roleRepository.findAll();
        if(allRoles.isEmpty()) {
            throw new NotFoundException("Ruoli non trovati");
        } else return allRoles;
    }

    @Transactional
    public RoleEntity createRole(@Valid RoleEntity role, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(HandelValidationError.getAllErrors(result));
        } else {
            // Check the role code
            if (role.getRoleCode() != 3 && role.getRoleCode() != 5 && role.getRoleCode() != 7) {
                throw new ValidationException("Invalid data entry (%d)\n" +
                        "*****************\n" +
                        "il codice del ruolo deve essere uno di questi valori (3, 5, 7)\n".formatted(role.getRoleCode()));
            } else {
                return roleRepository.save(role);

            }
        }
    }

}
