package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ActividadDTO;

import java.util.List;

public interface IActividadService {
    List<ActividadDTO> getActividadPorLugar(String lugar);
}
