package it.MSUsers.MSUsers.entity;

import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.json.simple.JSONObject;

import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="db_account_entity")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 64 characters", min = 3, max = 64)
    @Column(nullable = false, length = 64, unique = true)
    private String username;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 64 characters", min = 3, max = 64)
    @Email(message = "Invalid Email")
    @Column(nullable = false, length = 64, unique = true)
    private String email;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This value cannot contain less than 8 or more than 64 characters", min = 8, max = 64)
    @Column(nullable = false, length = 64)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserEntity user;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean accountLoginCurrentStatus;

    @Column(columnDefinition = "JSON", nullable = true)
    private String logInDetails;



}
