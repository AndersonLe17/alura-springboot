package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.medico.MedicoRespository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidMedicoActive implements ConsultaValidation{

    private final MedicoRespository medicoRespository;

    @Override
    public void valid(ConsultaRequest consulta) {
        if (consulta.getIdMedico() == null) return;

        Boolean isActivo = medicoRespository.findActivoById(consulta.getIdMedico());

        if (!isActivo) throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
    }
}
