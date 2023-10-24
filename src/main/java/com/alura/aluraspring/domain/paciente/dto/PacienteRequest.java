package com.alura.aluraspring.domain.paciente.dto;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PacienteRequest {
    @NotBlank
    String nombre;
    @NotBlank @Email
    String email;

    @NotBlank @Size(min = 0, max = 15)
    String telefono;

    //@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    @NotBlank
    String documento;

    @NotNull @Valid
    DireccionRequest direccion;
}
