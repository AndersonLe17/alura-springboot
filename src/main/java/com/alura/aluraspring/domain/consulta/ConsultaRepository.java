package com.alura.aluraspring.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime firstTime, LocalDateTime lastTime);
    Boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime time);

}
