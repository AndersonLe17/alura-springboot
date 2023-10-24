package com.alura.aluraspring.domain.paciente.dto;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PacienteUpdateRequest {
    @NotNull
    Long id;
    String nombre;
    String telefono;
    DireccionRequest direccion;
}
