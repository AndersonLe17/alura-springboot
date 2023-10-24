package com.alura.aluraspring.domain.direccion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Direccion {
    @Column(nullable = false)
    private String calle;
    private String numero;
    private String complemento;
    @Column(nullable = false)
    private String distrito;
    @Column(nullable = false)
    private String ciudad;
}
