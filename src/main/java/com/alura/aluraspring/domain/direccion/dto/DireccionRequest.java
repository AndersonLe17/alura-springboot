package com.alura.aluraspring.domain.direccion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DireccionRequest {
        @NotBlank String calle;
        @NotBlank String distrito;
        @NotBlank String ciudad;
        @NotBlank String numero;
        @NotBlank String complemento;
}
