package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.ConsultaRepository;
import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidPacienteConsulta implements ConsultaValidation{

    private final ConsultaRepository consultaRepository;

    @Override
    public void valid(ConsultaRequest consulta) {
        LocalDateTime openHour = consulta.getFecha().withHour(7);
        LocalDateTime closeHour = consulta.getFecha().withHour(18);

        Boolean existConsultaPaciente = consultaRepository.existsByPacienteIdAndFechaBetween(consulta.getIdPaciente(), openHour, closeHour);

        if (existConsultaPaciente) throw new ValidationException("No se puede permitir agendar mas de una consulta en el mismo dia para el mismo paciente");
    }
}
