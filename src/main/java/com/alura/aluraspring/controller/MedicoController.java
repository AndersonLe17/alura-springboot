package com.alura.aluraspring.controller;

import com.alura.aluraspring.domain.direccion.dto.DireccionRequest;
import com.alura.aluraspring.domain.medico.Medico;
import com.alura.aluraspring.domain.medico.dto.MedicoResponse;
import com.alura.aluraspring.domain.medico.dto.MedicoResponseList;
import com.alura.aluraspring.domain.medico.MedicoRespository;
import com.alura.aluraspring.domain.medico.dto.MedicoRequest;
import com.alura.aluraspring.domain.medico.dto.MedicoUpdateRequest;
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
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private final MedicoRespository medicoRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<MedicoResponse> registrarMedico(@RequestBody @Valid MedicoRequest medicoDTO,
                                          UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(modelMapper.map(medicoDTO, Medico.class));

        URI url =uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(modelMapper.map(medico, MedicoResponse.class));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseList>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion)
                        .map(medico -> modelMapper.map(medico, MedicoResponseList.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return  ResponseEntity.ok(modelMapper.map(medico, MedicoResponse.class));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody MedicoUpdateRequest medicoDTO) {
        Medico medico = medicoRepository.getReferenceById(medicoDTO.getId());
        if (medicoDTO.getNombre() != null) medico.setNombre(medicoDTO.getNombre());
        if (medicoDTO.getDocumento() != null) medico.setDocumento(medicoDTO.getDocumento());
        if (medicoDTO.getDireccion() != null) {
            DireccionRequest direccionDTO = medicoDTO.getDireccion();
            if (direccionDTO.getCalle() != null) medico.getDireccion().setCalle(direccionDTO.getCalle());
            if (direccionDTO.getCiudad() != null) medico.getDireccion().setCiudad(direccionDTO.getCiudad());
            if (direccionDTO.getNumero() != null) medico.getDireccion().setNumero(direccionDTO.getNumero());
            if (direccionDTO.getDistrito() != null) medico.getDireccion().setDistrito(direccionDTO.getDistrito());
            if (direccionDTO.getComplemento() != null) medico.getDireccion().setComplemento(direccionDTO.getComplemento());
        };

        return ResponseEntity.ok(modelMapper.map(medico, MedicoResponse.class));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.setActivo(false);

        return  ResponseEntity.noContent().build();
    }

}
