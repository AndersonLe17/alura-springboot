package com.alura.aluraspring.infra.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TokenJWT {
    String jwtToken;
}
