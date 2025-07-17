package it.MSUsers.MSUsers.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.MSUsers.MSUsers.validator.ValidateDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

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

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 3 e più di 128 caratteri", min = 3, max = 128)
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 3 e più di 128 caratteri", min = 3, max = 128)
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @JsonFormat(pattern = "yyyy-mm-dd")
    @ValidateDateRange(message = "Data non valida perchè supera i range preimpostati dal sistema\n" +
            "Inserisci una data valida ",
            min_year_num =  18,
            max_year_num = 100)
    private LocalDate birthDate;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean flagDelete;

    @ManyToMany
    @JoinTable(name = "db_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> userRole;


}
