package it.MSUsers.MSUsers.controller;

import it.MSUsers.MSUsers.entity.RoleEntity;
import it.MSUsers.MSUsers.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/roles/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@Valid @RequestBody RoleEntity role, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role, result));
    }

}
