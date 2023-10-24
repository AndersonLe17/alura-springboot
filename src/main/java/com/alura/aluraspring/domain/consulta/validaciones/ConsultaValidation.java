package com.alura.aluraspring.domain.consulta.validaciones;

import com.alura.aluraspring.domain.consulta.dto.ConsultaRequest;

public interface ConsultaValidation {
    void valid(ConsultaRequest consulta);
}
