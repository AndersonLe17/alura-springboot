package com.alura.aluraspring.domain.paciente;

import com.alura.aluraspring.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(unique = true, nullable = false)
    private String documento;

    @Embedded
    private Direccion direccion;

    @Column(nullable = false)
    private Boolean activo = true;
}
