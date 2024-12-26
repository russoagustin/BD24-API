package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.ActividadDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IActividadRepository {
    Optional<List<ActividadDTO>> getActividadPorLugar(String lugar) throws SQLException;
}
