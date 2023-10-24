package com.alura.aluraspring.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRespository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable pagination);

    @Query("""
            select m from Medico m where m.activo=true and m.especialidad=:especialidad 
            and m.id not in ( select c.medico.id from Consulta c where c.fecha=:fecha)
            order by rand()
            limit 1
            """)
    Medico findMedicoByEspecialidadAndFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("select m.activo from Medico m where m.id = :id")
    Boolean findActivoById(Long id);
}
