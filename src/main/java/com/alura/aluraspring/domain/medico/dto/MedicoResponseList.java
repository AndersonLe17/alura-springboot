package com.alura.aluraspring.domain.medico.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MedicoResponseList {
    Long id;
    String nombre;
    String especialidad;
    String documento;
    String email;
}
