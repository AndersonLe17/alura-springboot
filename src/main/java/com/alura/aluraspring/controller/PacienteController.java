package com.alura.aluraspring.controller;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import com.alura.aluraspring.domain.paciente.Paciente;
import com.alura.aluraspring.domain.paciente.PacienteRepository;
import com.alura.aluraspring.domain.paciente.dto.PacienteRequest;
import com.alura.aluraspring.domain.paciente.dto.PacienteResponse;
import com.alura.aluraspring.domain.paciente.dto.PacienteResponseList;
import com.alura.aluraspring.domain.paciente.dto.PacienteUpdateRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid PacienteRequest pacienteDTO, UriComponentsBuilder uriBuilder) {
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        pacienteRepository.save(paciente);
        URI url = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(url).body(modelMapper.map(paciente, PacienteResponse.class));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponseList>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        Page<PacienteResponseList> pacientes = pacienteRepository.findAllByActivoTrue(paginacion)
                .map(paciente -> modelMapper.map(paciente, PacienteResponseList.class));

        return ResponseEntity.ok(pacientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid PacienteUpdateRequest pacienteDTO) {
        Paciente paciente = pacienteRepository.getReferenceById(pacienteDTO.getId());
        if (pacienteDTO.getNombre() !=null) paciente.setNombre(pacienteDTO.getNombre());
        if (pacienteDTO.getTelefono() !=null) paciente.setTelefono(pacienteDTO.getTelefono());
        if (pacienteDTO.getDireccion() != null) {
            DireccionRequest direccionDTO = pacienteDTO.getDireccion();
            if (direccionDTO.getCalle() != null) paciente.getDireccion().setCalle(direccionDTO.getCalle());
            if (direccionDTO.getCiudad() != null) paciente.getDireccion().setCiudad(direccionDTO.getCiudad());
            if (direccionDTO.getNumero() != null) paciente.getDireccion().setNumero(direccionDTO.getNumero());
            if (direccionDTO.getDistrito() != null) paciente.getDireccion().setDistrito(direccionDTO.getDistrito());
            if (direccionDTO.getComplemento() != null) paciente.getDireccion().setComplemento(direccionDTO.getComplemento());
        };

        return ResponseEntity.ok(modelMapper.map(paciente, PacienteResponse.class));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.setActivo(false);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(modelMapper.map(paciente, PacienteResponse.class));
    }

}
