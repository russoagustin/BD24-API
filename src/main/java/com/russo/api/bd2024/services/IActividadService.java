package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ActividadDTO;

import java.util.List;
import java.util.Optional;

public interface IActividadService {
    Optional<List<ActividadDTO>> getActividadPorLugar(String lugar);
}
