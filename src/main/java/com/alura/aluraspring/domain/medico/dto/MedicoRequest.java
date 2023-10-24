package com.alura.aluraspring.domain.medico.dto;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import com.alura.aluraspring.domain.medico.Especialidad;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MedicoRequest {
        @NotBlank String nombre;
        @NotBlank @Email String email;
        @NotBlank String telefono;
        @NotBlank @Pattern(regexp = "\\d{4,6}") String documento;
        @NotNull Especialidad especialidad;
        @NotNull @Valid DireccionRequest direccion;
}
