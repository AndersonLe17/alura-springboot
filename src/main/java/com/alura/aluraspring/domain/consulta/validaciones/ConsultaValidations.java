package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.ConsultaRepository;
import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

//@RequiredArgsConstructor
public class ConsultaValidations {
//
//    private final PacienteRepository pacienteRepository;
//    private final ConsultaRepository consultaRepository;
//    public void validSchedule(ConsultaRequest consultaDTO) {
//        Boolean isSunday = DayOfWeek.SUNDAY.equals(consultaDTO.getFecha().getDayOfWeek());
//        Boolean isBeforeOpen = consultaDTO.getFecha().getHour() < 7;
//        Boolean isAfterClose = consultaDTO.getFecha().getHour() > 19;
//
//        if (isSunday || isBeforeOpen || isAfterClose) throw new ValidationException("El horario de atencion de la clinica es de lunes a sabado, de 7:00 a 19:00 horas");
//    }
//
//    public void validAnticipation(ConsultaRequest consultaDTO) {
//        LocalDateTime now = LocalDateTime.now();
//
//        Boolean differenceTime = Duration.between(now, consultaDTO.getFecha()).toMinutes() < 30;
//
//        if (differenceTime) throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipacion");
//    }
//
//    public void validPacienteActive(ConsultaRequest consultaDTO) {
//        if (consultaDTO.getIdPaciente() == null) return;
//
//        Boolean isActivo = pacienteRepository.findActivoById(consultaDTO.getIdPaciente());
//
//        if (!isActivo) throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
//    }
//
//    public void validPacienteConsulta(ConsultaRequest consultaDTO) {
//        LocalDateTime openHour = consultaDTO.getFecha().withHour(7);
//        LocalDateTime closeHour = consultaDTO.getFecha().withHour(18);
//
//        Boolean existConsultaPaciente = consultaRepository.existsByPacienteIdAndFechaBetween(consultaDTO.getIdPaciente(), openHour, closeHour);
//
//        if (existConsultaPaciente) throw new ValidationException("No se puede permitir agendar mas de una consulta en el mismo dia para el mismo paciente");
//    }
//
//    public void validMedicoConsulta(ConsultaRequest consultaDTO) {
//        if (consultaDTO.getIdPaciente() == null) return;
//
//        Boolean existConsultaMedico = consultaRepository.existsByMedicoIdAndFecha(consultaDTO.getIdPaciente(), consultaDTO.getFecha());
//
//        if (existConsultaMedico) throw new ValidationException("No se puede permitir agendar mas de una consulta al mismo medico para el horario ingresado");
//    }
}
