package com.alura.aluraspring.domain.consulta.dto;

import com.alura.aluraspring.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ConsultaRequest {
    Long id;
    @NotNull
    Long idPaciente;
    Long idMedico;
    @NotNull @Future
    LocalDateTime fecha;
    Especialidad especialidad;
}
