package it.MSUsers.MSUsers.entity;

import it.MSUsers.MSUsers.validator.ValidateRoleCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="db_role_entity")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 128 characters", min = 3, max = 128)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 10 or more than 5000 characters", min = 10, max = 5000)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "This field (%s) does not accept a null value")
    @Positive(message = "Thies field (%s) accepts numbers")
    @ValidateRoleCode(message = "Thies field must contain these values (3,5,7)", acceptedValue = {3,5,7})
    @Column(nullable = false)
    private int roleCode;
}
