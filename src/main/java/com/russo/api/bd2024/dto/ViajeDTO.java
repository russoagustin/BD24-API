package com.russo.api.bd2024.dto;

import java.time.LocalDate;

public record ViajeDTO( Integer id,
                        String transporte,
                        String inicio,
                        String fin,
                        String destino,
                        String nombre_evento) {
}
