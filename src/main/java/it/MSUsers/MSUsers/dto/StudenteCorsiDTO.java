package it.MSUsers.MSUsers.dto;

import it.MSUsers.MSUsers.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudenteCorsiDTO {
    private UserEntity utente;
    private List<CorsoDTO> corsi;
}
