package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidAnticipation implements ConsultaValidation{
    @Override
    public void valid(ConsultaRequest consulta) {
        LocalDateTime now = LocalDateTime.now();

        Boolean differenceTime = Duration.between(now, consulta.getFecha()).toMinutes() < 30;

        if (differenceTime) throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipacion");
    }
}
