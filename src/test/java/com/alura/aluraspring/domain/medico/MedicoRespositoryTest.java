package com.alura.aluraspring.domain.medico;

import com.alura.aluraspring.domain.consulta.Consulta;
import com.alura.aluraspring.domain.direccion.Direccion;
import com.alura.aluraspring.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRespositoryTest {
    @Autowired
    private MedicoRespository medicoRespository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void findMedicoByEspecialidadAndFechaEscenario1() {
        // Given
        LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        Medico medico = registrarMedico("Anderson", "anderson@gmail.com","996282983","775316",Especialidad.CARDIOLOGIA);
        Paciente paciente = registrarPaciente("Juan", "juan@gmail.com", "936159126", "434698");
        registrarConsulta(medico,paciente,nextMonday);
        // When
        Medico medicoLibre = medicoRespository.findMedicoByEspecialidadAndFecha(Especialidad.CARDIOLOGIA, nextMonday);
        // Then
        assertThat(medicoLibre).isNull();

    }

    @Test
    @DisplayName("Deberia retornar un medico cuando realice la consulta en la BD en ese horario")
    void findMedicoByEspecialidadAndFechaEscenario2() {
        // Given
        LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        Medico medico = registrarMedico("Anderson", "anderson@gmail.com","996282983","775316",Especialidad.CARDIOLOGIA);
        // When
        Medico medicoLibre = medicoRespository.findMedicoByEspecialidadAndFecha(Especialidad.CARDIOLOGIA, nextMonday);
        // Then
        assertThat(medicoLibre).isEqualTo(medico);

    }

    private Medico registrarMedico(String nombre, String email, String telefono, String documento, Especialidad especialidad) {
        Medico medico = new Medico(null,nombre, email, telefono, documento, true,especialidad, new Direccion("Calle Test","7357","T","Tester","Test"));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String telefono, String documento) {
        Paciente paciente = new Paciente(null,nombre, email, telefono, documento, new Direccion("Calle Test","7357","T","Tester","Test"), true);
        entityManager.persist(paciente);
        return paciente;
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        entityManager.persist(new Consulta(null, medico, paciente, fecha));
    }
}