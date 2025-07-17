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
            throw new NotFoundException("Roles not found");
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

    @Transactional
    public RoleEntity changeRole(Long id, @Valid RoleEntity new_role, BindingResult result){
        RoleEntity role = roleRepository.getRoleById(id).orElseThrow(()-> new NotFoundException("Role not found with this id (%d)".formatted(id)));
        if (result.hasErrors()) throw new DataValidationException(HandelValidationError.getAllErrors(result));
        else {
            role.setName(new_role.getName());
            role.setDescription(new_role.getDescription());
            role.setRoleCode(new_role.getRoleCode());
            return roleRepository.save(role);
        }
    }

//    @Transactional
//    public RoleEntity updateAttribute(Long id,
//                                      Optional<Boolean> name,
//                                      Optional<Boolean> description,
//                                      Optional<Boolean> roleCode,
//                                      @Valid RoleEntity new_role,
//                                      BindingResult result)
//    {
//        RoleEntity role = roleRepository.getRoleById(id).orElseThrow(() -> new NotFoundException("Role not found with this id (%d)" .formatted(id)));
//        if(!name.isEmpty() && name.get()) {
//            if (result.getFieldErrors("name").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "name"));
//            else role.setName(new_role.getName());
//        }
//        if (!description.isEmpty() && description.get()) {
//            if (result.getFieldErrors("description").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "description"));
//            else role.setDescription(new_role.getDescription());
//        }
//        if (!roleCode.isEmpty() && roleCode.get()) {
//            if (result.getFieldErrors("roleCode").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "roleCode"));
//            else role.setRoleCode(new_role.getRoleCode());
//        }
//
//        if(name.isEmpty() && description.isEmpty() && roleCode.isEmpty()) throw new DataValidationException("You should filter what attributes you wanna update");
//        else {
//            return roleRepository.save(role);
//        }
//
//
//    }

    @Transactional
    public RoleEntity updateAttribute(Long id,
                                      @Valid RoleEntity new_role,
                                      BindingResult result)
    {
        RoleEntity role = roleRepository.getRoleById(id).orElseThrow(() -> new NotFoundException("Role not found with this id (%d)" .formatted(id)));
        if(new_role.getName() != null) {
            if (result.getFieldErrors("name").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "name"));
            else role.setName(new_role.getName());
        }
        if (new_role.getDescription() != null) {
            if (result.getFieldErrors("description").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "description"));
            else role.setDescription(new_role.getDescription());
        }
        if (!Optional.of(new_role.getRoleCode()).isEmpty()) {
            if (result.getFieldErrors("roleCode").size() > 0) throw new DataValidationException(HandelValidationError.getErrorsOfField(result, "roleCode"));
            else role.setRoleCode(new_role.getRoleCode());
        }

        if(new_role.getDescription() == null && new_role.getDescription() == null && Optional.of(new_role.getRoleCode()).isEmpty()) throw new DataValidationException("You should filter what attributes you wanna update");
        else {
            return roleRepository.save(role);
        }


    }

}
