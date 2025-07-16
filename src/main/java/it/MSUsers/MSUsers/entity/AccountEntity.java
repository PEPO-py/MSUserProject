package it.MSUsers.MSUsers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.lang.annotation.Documented;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="db_account_entity")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 3 e più di 64 caratteri", min = 3, max = 64)
    @Column(nullable = false, length = 64, unique = true)
    private String username;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 3 e più di 64 caratteri", min = 3, max = 64)
    @Email(message = "Email non valida")
    @Column(nullable = false, length = 64, unique = true)
    private String email;

    @NotBlank(message = "Questo campo (%s) non può essere una stringa vuota")
    @NotNull(message = "Questo campo (%s) non accetta una valore null")
    @Size(message = "Questo campo non può contenere meno di 8 e più di 64 caratteri", min = 8, max = 64)
    @Column(nullable = false, length = 64)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserEntity user;

}
