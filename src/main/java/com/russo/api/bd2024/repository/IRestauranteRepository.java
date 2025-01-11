package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.RestauranteDTO;

import java.sql.SQLException;
import java.util.List;

public interface IRestauranteRepository {
    List<RestauranteDTO> getRestaurantes();
    List<RestauranteDTO> getRestaurantes(String tipo);
}
