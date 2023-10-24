package com.alura.aluraspring.domain.paciente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PacienteResponseList {
    Long id;
    String nombre;
    String email;
    String documento;
}
