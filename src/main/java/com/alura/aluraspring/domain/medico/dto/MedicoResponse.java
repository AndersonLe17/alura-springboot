package com.alura.aluraspring.domain.medico.dto;

import com.alura.aluraspring.domain.direccion.dto.DireccionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MedicoResponse {
    Long id;
    String nombre;
    String email;
    String telefono;
    String documento;
    DireccionResponse direccion;
}
