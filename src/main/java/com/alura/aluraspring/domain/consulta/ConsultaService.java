package com.alura.aluraspring.domain.consulta;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.consulta.validaciones.ConsultaValidation;
import com.alura.aluraspring.domain.medico.Medico;
import com.alura.aluraspring.domain.medico.MedicoRespository;
import com.alura.aluraspring.domain.paciente.Paciente;
import com.alura.aluraspring.domain.paciente.PacienteRepository;
import com.alura.aluraspring.infra.errors.HandleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRespository medicoRepository;
    private final ConsultaRepository consultaRepository;
    private final List<ConsultaValidation> consultaValidations;

    public Consulta agendar(ConsultaRequest consultaDTO){
        if(!pacienteRepository.findById(consultaDTO.getIdPaciente()).isPresent()) {
            throw new HandleValidation("este id para el paciente no fue encontrado");
        }
        if(consultaDTO.getIdMedico() != null && !medicoRepository.existsById(consultaDTO.getIdMedico())) {
            throw new HandleValidation("este id para el medico no fue encontrado");
        }

        consultaValidations.forEach(validation -> validation.valid(consultaDTO));

        Paciente paciente = pacienteRepository.findById(consultaDTO.getIdPaciente()).get();
        Medico medico = seleccionarMedico(consultaDTO);
        if (medico == null) throw new HandleValidation("No existen medicos disponibles para este horario y especialidad");
        Consulta consulta = new Consulta(null, medico, paciente, consultaDTO.getFecha());

        consultaRepository.save(consulta);

        return consulta;
    }

    private Medico seleccionarMedico(ConsultaRequest consultaDTO) {
        if(consultaDTO.getIdMedico() != null){
            return medicoRepository.getReferenceById(consultaDTO.getIdMedico());
        }
        if(consultaDTO.getEspecialidad() == null){
            throw new HandleValidation("debe seleccionarse una especialidad para el medico");
        }

        return medicoRepository.findMedicoByEspecialidadAndFecha(consultaDTO.getEspecialidad(), consultaDTO.getFecha());
    }

}
