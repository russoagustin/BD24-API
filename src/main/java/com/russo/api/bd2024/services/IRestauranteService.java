package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.RestauranteDTO;

import java.util.List;

public interface IRestauranteService {
    List<RestauranteDTO> getRestaurantes(String tipo);
}
