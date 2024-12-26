package com.russo.api.bd2024.dto;

public record ActividadDTO(
        Long id,
        String actividad,
        String descripcion,
        String hora,
        String lugar,
        String url
) {
}
