package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidSchedule implements ConsultaValidation{
    @Override
    public void valid(ConsultaRequest consulta) {
        Boolean isSunday = DayOfWeek.SUNDAY.equals(consulta.getFecha().getDayOfWeek());
        Boolean isBeforeOpen = consulta.getFecha().getHour() < 7;
        Boolean isAfterClose = consulta.getFecha().getHour() > 19;

        if (isSunday || isBeforeOpen || isAfterClose) throw new ValidationException("El horario de atencion de la clinica es de lunes a sabado, de 7:00 a 19:00 horas");
    }
}
