package com.alura.aluraspring.domain.direccion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DireccionResponse {
    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;
}
