package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.RestauranteDTO;

import java.util.List;
import java.util.Optional;

public interface IRestauranteService {
    Optional<List<RestauranteDTO>> getRestaurantes();
    Optional<List<RestauranteDTO>> getRestaurantes(String tipo);
}
