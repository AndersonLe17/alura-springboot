package com.alura.aluraspring.controller;

import com.alura.aluraspring.domain.consulta.Consulta;
import com.alura.aluraspring.domain.consulta.ConsultaService;
import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;
import com.alura.aluraspring.domain.consulta.dto.ConsultaResponse;
import com.alura.aluraspring.domain.medico.Especialidad;
import com.alura.aluraspring.domain.medico.Medico;
import com.alura.aluraspring.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<ConsultaRequest> consultaRequestJacksonTester;
    @Autowired
    private JacksonTester<ConsultaResponse> consultaResponseJacksonTester;
    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deberia retornar estado HTTP 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void agendarEscenario1() throws Exception{
        // Given
        MockHttpServletResponse response = mockMvc.perform(post("/consultas")).andReturn().getResponse(); // When
        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado HTTP 200 cuando los datos ingresados sean validos")
    @WithMockUser
    void agendarEscenario2() throws Exception{
        // Given
        LocalDateTime fecha = LocalDateTime.now().plusHours(1);
        Especialidad especialidad = Especialidad.CARDIOLOGIA;
        Consulta consulta = new Consulta(null, new Medico(4L,null,null,null,null,true,especialidad,null),
                                new Paciente(2L, null, null,null, null, null, true), fecha);
        // When
        when(consultaService.agendar(any())).thenReturn(consulta);

        MockHttpServletResponse response = mockMvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaRequestJacksonTester.write(new ConsultaRequest(null,2L,4L, fecha, especialidad)).getJson()))
                .andReturn().getResponse();
        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String jsonEsperado = consultaResponseJacksonTester.write(new ConsultaResponse(null,2L,4L, fecha)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}