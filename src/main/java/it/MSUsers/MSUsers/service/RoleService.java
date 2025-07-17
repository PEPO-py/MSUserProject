package it.MSUsers.MSUsers.service;

import it.MSUsers.MSUsers.entity.RoleEntity;
import it.MSUsers.MSUsers.exception.DataValidationException;
import it.MSUsers.MSUsers.exception.NotFoundException;
import it.MSUsers.MSUsers.repository.RoleRepository;
import it.MSUsers.MSUsers.utils.HandelValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public RoleEntity getRole(long id) {
        return roleRepository.getRoleById(id).orElseThrow(() -> new NotFoundException("Role not found with id (%d)".formatted(id)));
    }

    @Transactional
    public RoleEntity createRole(@Valid RoleEntity role, BindingResult result) {
        if (result.hasErrors()) {
            throw new DataValidationException(HandelValidationError.getAllErrors(result));
        } else  return roleRepository.save(role);
    }

}
