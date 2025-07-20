package it.MSUsers.MSUsers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 64 characters", min = 3, max = 64)
    private String username;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This value cannot contain less than 8 or more than 64 characters", min = 8, max = 64)
    private String password;
}
