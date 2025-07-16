package it.MSUsers.MSUsers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="db_role_entity")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 3 e più di 128 caratteri", min = 3, max = 128)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può avere più di 5000 e meno di 10 caratteri", min = 10, max = 5000)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Positive(message = "Questo campo (%s) accetta numeri")
    @Column(columnDefinition = "INT CHECK (roleCode IN (3, 5, 7))")
    private int roleCode;
}
