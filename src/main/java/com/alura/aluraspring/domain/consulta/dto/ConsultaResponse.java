package com.alura.aluraspring.domain.consulta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ConsultaResponse {
    Long id;
    Long idPaciente;
    Long idMedico;
    LocalDateTime fecha;
}
