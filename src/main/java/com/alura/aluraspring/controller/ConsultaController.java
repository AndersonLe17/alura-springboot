package com.alura.aluraspring.controller;

import com.alura.aluraspring.domain.consulta.Consulta;
import com.alura.aluraspring.domain.consulta.ConsultaService;
import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.consulta.dto.ConsultaResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid ConsultaRequest consultaDTO) {
        Consulta consulta = consultaService.agendar(consultaDTO);

        return ResponseEntity.ok(modelMapper.map(consulta, ConsultaResponse.class));
    }

}
