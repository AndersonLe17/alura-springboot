package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidPacienteActive implements ConsultaValidation{

    private final PacienteRepository pacienteRepository;

    @Override
    public void valid(ConsultaRequest consulta) {
        if (consulta.getIdPaciente() == null) return;

        Boolean isActivo = pacienteRepository.findActivoById(consulta.getIdPaciente());

        if (!isActivo) throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
    }
}
