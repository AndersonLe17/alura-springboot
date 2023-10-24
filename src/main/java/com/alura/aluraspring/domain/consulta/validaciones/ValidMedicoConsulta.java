package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.ConsultaRepository;
import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidMedicoConsulta implements ConsultaValidation{

    private final ConsultaRepository consultaRepository;

    @Override
    public void valid(ConsultaRequest consulta) {
        if (consulta.getIdPaciente() == null) return;

        Boolean existConsultaMedico = consultaRepository.existsByMedicoIdAndFecha(consulta.getIdMedico(), consulta.getFecha());

        if (existConsultaMedico) throw new ValidationException("No se puede permitir agendar mas de una consulta al mismo medico para el horario ingresado");
    }
}
