package it.MSUsers.MSUsers.DAO;



import com.fasterxml.jackson.annotation.JsonFormat;
import it.MSUsers.MSUsers.validator.ValidateDateRange;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDAO {
    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 64 characters", min = 3, max = 64)
    private String username;

    @NotBlank(message = "This field (%s) cannot be an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 64 characters", min = 3, max = 64)
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This value cannot contain less than 8 or more than 64 characters", min = 8, max = 64)
    private String password;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 128 characters", min = 3, max = 128)
    private String firstName;

    @NotBlank(message = "This field (%s) cannot contain an empty string")
    @NotNull(message = "This field (%s) does not accept a null value")
    @Size(message = "This field cannot contain less than 3 or more than 128 characters", min = 3, max = 128)
    private String lastName;

    @NotNull(message = "This field (%s) does not accept a null value")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ValidateDateRange(message = "Date is invalid because it's over system default range\n" +
            "Insert a valid date ",
            min_year_num =  18,
            max_year_num = 100)
    private String birthDate;
}
