package com.russo.api.bd2024.dto;

public record RestauranteDTO(
        String nombre,
        String direccion,
        String hora_apertura,
        String hora_cierre,
        String url,
        String descripcion
) {
}
