package com.alura.aluraspring.domain.medico.dto;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MedicoUpdateRequest {
    @NotNull
    Long id;
    String nombre;
    String documento;
    DireccionRequest direccion;
}
