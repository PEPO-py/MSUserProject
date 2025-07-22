package it.MSUsers.MSUsers.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.MSUsers.MSUsers.validator.ValidateDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="db_users_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 128 characters", min = 3, max = 128)
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 128 characters", min = 3, max = 128)
    @Column(nullable = false)
    private String lastName;

    @JsonIgnore
    @NotNull(message = "This field (%s) does not accept a null value")
    private LocalDate birthDate;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean flagDelete;

    @ManyToMany
    @JoinTable(name = "db_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> userRole;


}
