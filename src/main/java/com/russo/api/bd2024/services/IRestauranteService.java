package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.RestauranteDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IRestauranteService {
    Optional<List<RestauranteDTO>> getRestaurantes() throws SQLException;
    Optional<List<RestauranteDTO>> getRestaurantes(String tipo) throws SQLException;
}
