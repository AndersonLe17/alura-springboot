package com.alura.aluraspring.domain.paciente.dto;

import com.alura.aluraspring.domain.direccion.Direccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PacienteResponse {
    Long id;
    String nombre;
    String email;
    String documento;
    String telefono;
    Direccion direccion;
}
